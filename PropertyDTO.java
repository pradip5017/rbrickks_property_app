package dto;

//package com.rbrickks.property.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
	
    public PropertyDTO(String title2, String city2, Double rent2, String type2, String description2, String imageUrl2,
			Object object, Object object2) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
    private String title;
    private String city;
    private double rent;
    private String type;
    private String description;
    private String imageUrl;
    private Long userId;
    private String username; // Optional: to show owner's name

}
