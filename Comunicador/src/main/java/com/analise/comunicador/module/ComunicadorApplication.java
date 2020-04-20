package com.analise.comunicador.module;

import com.analise.comunicador.module.domain.mensagemcomunicacao.EnvioComunicacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.analise.comunicador.module" })
public class ComunicadorApplication implements CommandLineRunner {

	@Autowired
	EnvioComunicacoesService envioComunicacoesService;

	public static void main(String[] args) {
		SpringApplication.run(ComunicadorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.envioComunicacoesService.processaMensagensTopicoAnaliseDocumentos();
	}
}
