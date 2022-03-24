package com.example;

import com.example.domain.Employee;
import com.example.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate<String, Customer> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, Employee> kafkaTemplateEmployee;

    @PostMapping("/customers")
    public String produce(@RequestBody Customer customer){
        Customer customerExample = Customer.newBuilder()
                .setId(null).setFirstName("customer1").setLastName("prueba").setSalary(40000.0)
                .build();

        ListenableFuture<SendResult<String, Customer>> future = this.kafkaTemplate.sendDefault(customer);

        // opción 1: clase anónima
//        future.addCallback(new ListenableFutureCallback<>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                log.error("Error sending message", ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Customer> result) {
//                log.info("partition {}", result.getRecordMetadata().partition());
//                log.info("offset {}", result.getRecordMetadata().offset());
//            }
//        });

        // Opción 2: lambdas
        future.addCallback(
                // successCallback
                result -> log.info("partition {}, offset {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset()),
                // failureCallback
                exception -> log.error("Error sending message", exception)
        );

        return "Produciendo customer!";
    }

    @PostMapping("/employee")
    public String produceEmployee(@RequestBody Employee employee){
        Employee employeeExample = Employee.newBuilder()
                .setId(null)
                .setName("customer1")
                .setLastname("prueba")
                .setRol("Analyst")
                .setCar(Car.newBuilder().setId(null).setCompany("volkswagen").setPrice(2000L).setType("Vehicle").build())
                .build();

        ListenableFuture<SendResult<String, Employee>> future = this.kafkaTemplateEmployee.sendDefault(employee);

        // Opción 2: lambdas
        future.addCallback(
                // successCallback
                result -> log.info("partition {}, offset {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset()),
                // failureCallback
                exception -> log.error("Error sending message", exception)
        );

        return "Produciendo customer!";
    }

}
