package repository;


import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import model.Property;

import java.util.List;
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	
	   List<Property> findByCity(String city);
	    List<Property> findByType(String type);
	    List<Property> findByRentBetween(Double min, Double max);
	    List<Property> findByUserId(Long userId);

}
