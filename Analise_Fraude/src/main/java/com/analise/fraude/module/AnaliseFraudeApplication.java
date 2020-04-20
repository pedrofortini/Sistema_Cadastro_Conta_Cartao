package com.analise.fraude.module;

import com.analise.fraude.module.domain.mensagemanalisefraude.AnaliseFraudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.analise.fraude.module" })
public class AnaliseFraudeApplication implements CommandLineRunner {

	@Autowired
	AnaliseFraudeService analiseFraudeService;

	public static void main(String[] args) {
		SpringApplication.run(AnaliseFraudeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.analiseFraudeService.processaMensagensTopicoAnaliseFraude();
	}
}
