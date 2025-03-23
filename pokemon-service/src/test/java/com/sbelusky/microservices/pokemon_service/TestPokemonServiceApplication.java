package com.sbelusky.microservices.pokemon_service;

import org.springframework.boot.SpringApplication;

public class TestPokemonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PokemonServiceApplication::main).with(TestContainerConfig.class).run(args);
	}

}
