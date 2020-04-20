package com.analise.documentos.module;

import com.analise.documentos.module.domain.mensagemanalisedocumentos.AnaliseDocumentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.analise.documentos.module" })
public class AnaliseDocumentosApplication implements CommandLineRunner {

	@Autowired
	AnaliseDocumentosService analiseDocumentosService;

	public static void main(String[] args) {
		SpringApplication.run(AnaliseDocumentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.analiseDocumentosService.processaMensagensTopicoAnaliseDocumentos();
	}
}
