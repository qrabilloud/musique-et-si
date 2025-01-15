package com.musiqueetsi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.musiqueetsi.backend.MusiqueLogique;
import com.musiqueetsi.backend.model.Music;
//import com.musiqueetsi.backend.service.LoginService;
import com.musiqueetsi.backend.service.MusicService;
import com.musiqueetsi.model.MusiqueProperties;

@RestController
@RequestMapping("/music")
@CrossOrigin("http://localhost:8080")
public class MusicController {
	
	 @Autowired
	 private MusicService musicService;
	 
	 @GetMapping("/music_available")
	 public Music[] getMusicAvailable() {
		 return null;
	 }
	 
	 
	 @PostMapping("")
	 public String postMusique(@RequestParam("file") MultipartFile file,
			 					//@RequestParam("properties") MusiqueProperties properties,
			 					RedirectAttributes redirectAttributes) {
		 MusiqueProperties properties = new MusiqueProperties(0, "name", "auteur", "description", "15/01/2025", "agiles/", "30/01/2025");
		 String message = MusiqueLogique.writeMusique(file, properties);
		 redirectAttributes.addFlashAttribute("message", message);
		 return "redirect:/";
	 }
}
