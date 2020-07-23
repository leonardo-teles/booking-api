package com.boot.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String description;
	
	private String image;
}
