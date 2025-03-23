package com.sbelusky.microservices.appointment_service;

import org.springframework.boot.SpringApplication;

public class TestAppointmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(AppointmentServiceApplication::main).with(TestContainerConfig.class).run(args);
	}

}
