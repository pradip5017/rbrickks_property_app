package controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import dto.PropertyDTO;
import model.Property;
import model.User;
import repository.UserRepository;
import service.PropertyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addProperty(@RequestBody Property property, Authentication auth) {
        Optional<User> user = userRepository.findByEmail(auth.getName());
        if (user.isPresent()) {
            property.setUser(user.get()); // ✅ Set the logged-in user as the property owner
            Property saved = propertyService.saveProperty(property);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.status(401).body("User not found");
    }

    @GetMapping("/public/all")
    public List<PropertyDTO> getAll() {
        return propertyService.getAllProperties()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/public/filter")
    public List<PropertyDTO> filter(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minRent,
            @RequestParam(required = false) Double maxRent
    ) {
        List<Property> results = propertyService.getAllProperties();
        if (city != null) results = results.stream().filter(p -> p.getCity().equalsIgnoreCase(city)).toList();
        if (type != null) results = results.stream().filter(p -> p.getType().equalsIgnoreCase(type)).toList();
        if (minRent != null && maxRent != null) results = results.stream().filter(p -> p.getRent() >= minRent && p.getRent() <= maxRent).toList();
        return results.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        Optional<Property> property = propertyService.getPropertyById(id);

        if (property.isPresent()) {
            Property prop = property.get();

            // ✅ Check if property has a user set
            if (prop.getUser() != null && prop.getUser().getEmail().equals(auth.getName())) {
                propertyService.deleteProperty(id);
                return ResponseEntity.ok("Deleted");
            } else {
                return ResponseEntity.status(403).body("Not authorized to delete this property");
            }
        }

        return ResponseEntity.status(404).body("Property not found");
    }

    

private PropertyDTO convertToDto(Property p) {
    return new PropertyDTO(
    		p.getTitle(),
    		p.getCity(),
    		p.getRent(),
    		p.getType(),
    		p.getDescription(),
    		p.getImageUrl(),
    		p.getUser() != null ?p.getUser().getId():null,
    		p.getUser() != null?p.getUser().getUsername():null
    		
 );
}

}