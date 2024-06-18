package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ioc.Entorno;
import com.example.ioc.Saluda;
import com.example.ioc.SaludaEnImpl2;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	Saluda saludaEs;
	
	@Autowired
	Saluda saludaEn;
	
	@Autowired
	Entorno entorno;
	
//	@Autowired(required = false)
//	SaludaEnImpl2 saludaImpl;	

	@Override
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
		saludaEs.saluda("Mundo");
		saludaEn.saluda("Mundo");
		System.out.println(saludaEs.getContador());
		System.out.println(saludaEn.getContador());
		System.out.println(entorno.getContador());
	}
}
