package com.jarcps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarcps.Data.Dummy;
import com.jarcps.model.Jar;
import com.jarcps.service.EmailService;
import com.jarcps.service.JarService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jars")
public class JarController {
	

	@Autowired
    private JarService jarService;
	
	@Autowired
	private EmailService emailService;

    @GetMapping("/{id}")
    public ResponseEntity<Jar> getJarById(@PathVariable int id) {
        Jar jar = jarService.getJarById(id);
        if (jar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jar, HttpStatus.OK);
    }

    @PostMapping("/adjustQuantity")
    public ResponseEntity<Void> adjustQuantity(@RequestBody Jar jar) {
        jarService.adjustQuantity(jar.getId(), jar.getQuantity());
        System.out.println("Jar Quantity: "+ jar.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/notify")
    public ResponseEntity<Void> notifyBackend(@RequestBody Map<String, Integer> requestBody) {
    	int id = requestBody.get("id");
        Jar jar = jarService.getJarById(id);
        System.out.println(jar.getQuantity() + " " + jar.getPreviousQuantity());
        if (jar.getQuantity() < 200 && jar.getPreviousQuantity()>=200) {
            String toEmail = "jinesh1077@gmail.com";
            String subject = "Jar Quantity Notification";
            String message = "The quantity of jar is below threshold.";
            emailService.sendNotificationEmail(toEmail, subject, message);
            System.out.println("Notification email sent to: " + toEmail);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}