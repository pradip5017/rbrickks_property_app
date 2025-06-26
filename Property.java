package model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

//package com.rbrickks.property.model;

import jakarta.persistence.*; 
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;  
import lombok.*;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityScan("model")
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String city;

    private Double rent;

    private String type;

    private String description;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//	public Object getid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getRent() {
		return rent;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}