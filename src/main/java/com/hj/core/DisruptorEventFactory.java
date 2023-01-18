package com.hj.core;

import com.lmax.disruptor.EventFactory;

/**
 * 消息工厂
 *
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:23 AM
 */
public class DisruptorEventFactory implements EventFactory<DisruptorEvent> {
    @Override
    public DisruptorEvent newInstance() {
        return new DisruptorEvent();
    }

}
