package com.sales.proyectocoder;

import com.sales.proyectocoder.repository.ClientRepository;
import com.sales.proyectocoder.repository.InvoiceDetailRepository;
import com.sales.proyectocoder.repository.InvoiceRepository;
import com.sales.proyectocoder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectocoderApplication implements CommandLineRunner {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectocoderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}

}
