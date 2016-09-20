/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the
 * confidential and proprietary information of Colotnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Colotnet.com.
 */
package com.silence.service.impl;

import com.silence.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * 类KafkaServiceImpl.java的实现描述：发消息实现类
 */
@Service("kafkaService")
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    @Qualifier("kafkaTopicTest")
    MessageChannel channel;

    public void sendUserInfo(String topic, Object obj) {
        channel.send(MessageBuilder.withPayload(obj)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build());
    }

}
