package ru.job4j.pinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class PingerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingerApplication.class, args);
	}

}
