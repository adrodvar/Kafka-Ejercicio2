package com.example;

import com.example.domain.Employee;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "avrotopic")
    public void onCustomerSave(ConsumerRecord<String, Customer> message){

        Customer customer = message.value();
        log.info("customer {}", customer);
        log.info("partition {}", message.partition());
        log.info("offset {}", message.offset());

    }

    @KafkaListener(topics = "avrotopic")
    public void onEmployeeSave(ConsumerRecord<String, Employee> message){

        Employee employee = message.value();
        log.info("customer {}", employee);
        log.info("partition {}", message.partition());
        log.info("offset {}", message.offset());

    }
}
