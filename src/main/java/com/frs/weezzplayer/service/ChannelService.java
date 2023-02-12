package com.frs.weezzplayer.service;


import com.frs.weezzplayer.entity.Channel;
import com.frs.weezzplayer.entity.DeviceGroup;
import com.frs.weezzplayer.model.BlockingQueue;
import com.frs.weezzplayer.model.DeviceUpdate;
import com.frs.weezzplayer.repository.ChannelRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public List<Channel> getAllChannel() {
        return channelRepository.findAll();
    }

    public void delete(Long id) {
        channelRepository.deleteById(id);
    }
    public Channel updateChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel findChannelById(Long id) throws ChangeSetPersister.NotFoundException {
        return channelRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

    }


    }
