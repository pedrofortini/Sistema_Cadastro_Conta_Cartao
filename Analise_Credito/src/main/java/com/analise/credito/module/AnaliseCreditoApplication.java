package com.analise.credito.module;

import com.analise.credito.module.domain.mensagemanalisecredito.AnaliseCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.analise.credito.module" })
public class AnaliseCreditoApplication implements CommandLineRunner {

	@Autowired
	AnaliseCreditoService analiseCreditoService;

	public static void main(String[] args) {
		SpringApplication.run(AnaliseCreditoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.analiseCreditoService.processaMensagensTopicoAnaliseCredito();
	}
}
