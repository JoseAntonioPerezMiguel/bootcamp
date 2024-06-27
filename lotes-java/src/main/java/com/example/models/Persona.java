package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Persona {
	private long id;
	private String name;
	private String email;
	private String ip;
}
