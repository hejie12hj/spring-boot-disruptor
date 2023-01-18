package com.hj.core;

import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;

/**
 * 生产者
 *
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:36 AM
 */
public class DisruptorProducer {
    Logger logger = org.slf4j.LoggerFactory.getLogger(DisruptorProducer.class);

    private RingBuffer<DisruptorEvent> ringBuffer;

    public DisruptorProducer() {
    }

    public RingBuffer<DisruptorEvent> getRingBuffer() {
        return ringBuffer;
    }

    public void setRingBuffer(RingBuffer<DisruptorEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public DisruptorProducer(RingBuffer<DisruptorEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 生产消息
     *
     * @param type
     * @param jsonData
     */
    public void publishData(String type, String jsonData) {
        logger.info("生产消息：type={},jsonData={}", type, jsonData);
        long sequence = ringBuffer.next();
        try {
            DisruptorEvent event = ringBuffer.get(sequence);
            event.setType(type);
            event.setJsonData(jsonData);
        } catch (Exception e) {
            logger.error("生产消息异常：type={},jsonData={}", type, jsonData);
        } finally {
            ringBuffer.publish(sequence);
        }
    }


}
