package com.frs.weezzplayer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.function.Supplier;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeferredResultChannel<T> extends DeferredResult<T> {

    String identifier;

    public DeferredResultChannel(Long timeoutValue, Object timeoutResult) {
        super(timeoutValue, timeoutResult);
    }

    public DeferredResultChannel(Long timeoutValue, Supplier<?> timeoutResult) {
        super(timeoutValue, timeoutResult);
    }

    public DeferredResultChannel(Long timeoutValue) {
        super(timeoutValue);
    }


}
