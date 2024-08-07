package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;

import java.sql.Timestamp;

import com.example.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the film_actor database table.
 * 
 */
@Entity
@Table(name = "film_actor")
@NamedQuery(name = "FilmActor.findAll", query = "SELECT f FROM FilmActor f")
public class FilmActor extends EntityBase<FilmActor> implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmActorPK id;

	@Column(name = "last_update", insertable = false, updatable = false, nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name = "actor_id", nullable = false, insertable = false, updatable = false)
	@JsonManagedReference(value = "actor-filmActors")
	private Actor actor;

	// bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name = "film_id", nullable = false, insertable = false, updatable = false)
	@JsonManagedReference(value = "film-filmActors")
	private Film film;

	public FilmActor() {
	}

	public FilmActorPK getId() {
		return this.id;
	}

	public void setId(FilmActorPK id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@PrePersist
	public void checkId() {
		if (getId() == null) {
			setId(new FilmActorPK(getActor().getActorId(), getFilm().getFilmId()));
		}
	}

}