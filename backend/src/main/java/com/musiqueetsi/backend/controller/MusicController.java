package com.musiqueetsi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musiqueetsi.backend.model.Music;
import com.musiqueetsi.backend.service.LoginService;
import com.musiqueetsi.backend.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {
	
	 @Autowired
	 private MusicService musicService;
	 
	 @GetMapping("/music_available")
	 public Music[] getMusicAvailable() {
		 return null;
	 }
}
