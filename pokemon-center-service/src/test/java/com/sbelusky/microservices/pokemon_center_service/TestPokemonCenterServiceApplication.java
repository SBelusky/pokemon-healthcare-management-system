package com.sbelusky.microservices.pokemon_center_service;

import org.springframework.boot.SpringApplication;

public class TestPokemonCenterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PokemonCenterServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
