package com.etaskify.mailservice;

import com.etaskify.mailservice.dto.MailDto;
import com.etaskify.mailservice.service.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class MailServiceApplication  implements CommandLineRunner {

    private  final  ExampleService exampleService;

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        exampleService.sendWhenAssignedTask(new MailDto("Fuad","Shirinov","darkside.xe@gmail.com","taskify task"));
    }
}
