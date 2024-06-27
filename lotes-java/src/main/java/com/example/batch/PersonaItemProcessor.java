package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.models.Persona;
import com.example.models.PersonaDTO;


@Component
public class PersonaItemProcessor implements ItemProcessor<PersonaDTO, Persona>  {
	private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);
	@Override
	public Persona process(PersonaDTO item) throws Exception {
	if(item.getId() % 2 == 0 || "Male".equals(item.getGender())) return null;
	Persona rslt = new Persona(item.getId(), item.getSurname() + ", " + item.getName(),
			item.getEmail(), item.getIp());
	log.info("Procesando: " + item);
	return rslt;
	}
}
