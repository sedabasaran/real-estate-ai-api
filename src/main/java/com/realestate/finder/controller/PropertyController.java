package com.realestate.finder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.finder.dto.request.PropertyRequestDTO;
import com.realestate.finder.dto.response.PropertyResponseDTO;
import com.realestate.finder.entity.User;
import com.realestate.finder.security.JwtUtil;
import com.realestate.finder.service.PropertyService;
import com.realestate.finder.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

	private final PropertyService propertyService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public PropertyController(PropertyService propertyService, JwtUtil jwtUtil, UserService userService) {
        this.propertyService = propertyService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(
            @RequestBody PropertyRequestDTO request,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token); // ✅

        User user = userService.findUserEntityByEmail(email);

        PropertyResponseDTO response = propertyService.createProperty(request, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties() {
        List<PropertyResponseDTO> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable Long id) {
        PropertyResponseDTO property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable Long id,
                                                              @Valid @RequestBody PropertyRequestDTO requestDTO) {
        PropertyResponseDTO updatedProperty = propertyService.updateProperty(id, requestDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
