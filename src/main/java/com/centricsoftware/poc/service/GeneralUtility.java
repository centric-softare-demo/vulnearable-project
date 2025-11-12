package com.centricsoftware.poc.service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneralUtility {
	
	public ResponseEntity<String> insecureDeserialize(byte[] payload) {
	    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(payload))) {
	        Object obj = ois.readObject(); 
	        return ResponseEntity.ok("Deserialized object: " + obj.toString());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	}
	
	

}
