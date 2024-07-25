package app.controller;

import app.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private final KafkaProducer kafkaProducer;

    public Controller(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return "Success!";
    }

}