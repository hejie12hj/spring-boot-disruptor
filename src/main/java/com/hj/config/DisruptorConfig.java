package com.hj.config;

import com.hj.core.DisruptorEvent;
import com.hj.core.DisruptorEventFactory;
import com.hj.core.DisruptorHandle;
import com.hj.core.DisruptorProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:44 AM
 */
public class DisruptorConfig {


    @Value("${disruptor.bufferSize:1048576}")
    private int bufferSize;

    @Autowired
    @Qualifier("disTaskExecutor")
    private Executor executor;


    @Autowired
    DisruptorHandle disruptorHandle;

    @Autowired
    DisruptorProducer disruptorProducer;

    Disruptor<DisruptorEvent> disruptor;

    @PostConstruct
    public void init() {
        DisruptorEventFactory disruptorEventFactory = new DisruptorEventFactory();

        disruptor = new Disruptor<>(disruptorEventFactory, bufferSize, executor);

        disruptor.handleEventsWith(disruptorHandle);

        disruptor.start();

        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();

        disruptorProducer.setRingBuffer(ringBuffer);

    }


    @PreDestroy
    public void destroy() {
        disruptor.shutdown();
    }


}
