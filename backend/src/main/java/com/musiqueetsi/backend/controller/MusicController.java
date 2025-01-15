package com.musiqueetsi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.musiqueetsi.backend.model.MusiqueProperties;
import com.musiqueetsi.backend.service.MusiqueLogique;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/music")
public class MusicController {
	
	 @Autowired
	 private MusiqueLogique musicService;
	 
	 @GetMapping("/music_listenable")
	 public List<MusiqueProperties> getMusicListenable() {
		 
		 return musicService.getAllPropertiesPlayables();
	 }
	 
	 @GetMapping("/music_file/{id}")
	 public ResponseEntity<byte[]> getMusicFile(@PathVariable int id) {
		 // We get the music we want to listen
		 MusiqueProperties musicInformations = musicService.getPropertiesById(id);
		 byte[] musicBytes = musicService.getMusicFile(musicInformations);
		 
		 if (musicBytes.length > 0 ) {
			 return ResponseEntity.ok()
	                    .contentType(MediaType.parseMediaType("audio/mpeg"))
	                    .contentLength(musicBytes.length)
	                    .body(musicBytes);
		 }
		 else {
			 return ResponseEntity.notFound().build();
		 }
		
	 }
	 
	 
	 @PostMapping("")
	 public String postMusique(@RequestParam("file") MultipartFile file,
			 					@RequestParam("name") String name,
			 					@RequestParam("auteur") String auteur,
			 					@RequestParam("description") String description,
			 					@RequestParam("pathToMusiqueFile") String pathToMusiqueFile,
			 					@RequestParam("pathToMusiqueFile") String startDiffusion,
			 					@RequestParam("pathToMusiqueFile") String endDiffusion
			 ) {
		 MusiqueProperties properties = new MusiqueProperties(0, name, auteur, description, startDiffusion, pathToMusiqueFile, endDiffusion);
		 String message = MusiqueLogique.writeMusique(file, properties);
		 return message;
	 }
}