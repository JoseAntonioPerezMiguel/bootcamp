package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("es")
	Saluda saluda;
	
	@Autowired
	@Qualifier("en")
	Saluda saluda2;
	
	@Autowired
	Entorno entorno;
	
//	@Autowired(required = false)
//	SaludaEnImpl2 saludaImpl;	

	@Override
	public void run(String... args) throws Exception{
		System.err.println("Aplicación arrancada");
		saluda.saluda("Mundo");
		saluda2.saluda("Mundo");
		System.out.println(saluda.getContador());
		System.out.println(saluda2.getContador());
		System.out.println(entorno.getContador());
	}
}
