package com.example.models;

import lombok.Data;

@Data
public class PersonaDTO {
	private long id;
	private String name;
	private String surname;
	private String email;
	private String gender;
	private String ip;
}
