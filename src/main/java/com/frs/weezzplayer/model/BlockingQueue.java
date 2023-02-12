package com.frs.weezzplayer.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class BlockingQueue<NotificationAlert> extends LinkedBlockingQueue<NotificationAlert> {
}
