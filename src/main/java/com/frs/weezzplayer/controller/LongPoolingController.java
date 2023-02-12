package com.frs.weezzplayer.controller;

import com.frs.weezzplayer.model.BlockingQueue;
import com.frs.weezzplayer.model.Config.LongPollingConfig;
import com.frs.weezzplayer.model.DeferredResultChannel;
import com.frs.weezzplayer.model.DeviceUpdate;
import com.frs.weezzplayer.service.CampaignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/longpooling")
@AllArgsConstructor
@Slf4j
public class LongPoolingController {


    private final CampaignService campaignService;

    private final LongPollingConfig longPollingConfig;
    private final BlockingQueue<DeviceUpdate> queue;

    ArrayList<DeferredResultChannel> hangingRequests = new ArrayList<DeferredResultChannel>();


    @GetMapping("device/{identifier}")
    public DeferredResult<ResponseEntity<?>> deviceRequest(@PathVariable String identifier) {

        DeferredResultChannel<ResponseEntity<?>> handledRequest = new DeferredResultChannel<>(longPollingConfig.getTimeOut(), longPollingConfig.getResponse());

        handledRequest.onTimeout(new Runnable() {
            @Override
            public void run() {
                handledRequest.setResult(new ResponseEntity<>(longPollingConfig.getResponse(), HttpStatus.REQUEST_TIMEOUT));
            }
        });

        hangingRequests.add(handledRequest);
        handledRequest.setIdentifier(identifier);

        ForkJoinPool.commonPool().submit(() -> {
            try {
                DeviceUpdate deviceUpdate = queue.take();
                for (DeferredResultChannel hangingRequest : hangingRequests) {
                    if (deviceUpdate.getIdentifier().equals(hangingRequest.getIdentifier())) {
                        hangingRequest.setResult(new ResponseEntity<>(deviceUpdate.getCampaign(), HttpStatus.OK));
                    }
                }
                hangingRequests.removeIf(new Predicate<DeferredResultChannel>() {
                    @Override
                    public boolean test(DeferredResultChannel t) {
                        return t.hasResult();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return handledRequest;
    }


}
