package service;

//package com.rbrickks.property.service;

//import com.rbrickks.property.model.Property;
//import com.rbrickks.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import model.Property;
import repository.PropertyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
	
	 @Autowired
	    private PropertyRepository propertyRepository;

	    public Property saveProperty(Property property) {
	        return propertyRepository.save(property);
	    }

	    public List<Property> getAllProperties() {
	        return propertyRepository.findAll();
	    }

	    public Optional<Property> getPropertyById(Long id) {
	        return propertyRepository.findById(id);
	    }

	    public List<Property> getPropertiesByCity(String city) {
	        return propertyRepository.findByCity(city);
	    }

	    public List<Property> getPropertiesByType(String type) {
	        return propertyRepository.findByType(type);
	    }

	    public List<Property> getPropertiesByRentRange(Double min, Double max) {
	        return propertyRepository.findByRentBetween(min, max);
	    }

	    public List<Property> getPropertiesByUserId(Long userId) {
	        return propertyRepository.findByUserId(userId);
	    }

	    public void deleteProperty(Long id) {
	        propertyRepository.deleteById(id);
	    }

}
