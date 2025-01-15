package com.musiqueetsi.backend.model;

public class MusiqueProperties {
	private int id;
	private String name;
	private String auteur;
	private String description;
	private String startDiffusion;
	private String pathToMusiqueFile;
	private String endDiffusion;
	
	public MusiqueProperties() {
	}
	
	public MusiqueProperties(int id, String name, String auteur, String description, String startDiffusion,
			String pathToMusiqueFile, String endDiffusion) {
		this.id = id;
		this.name = name;
		this.auteur = auteur;
		this.description = description;
		this.startDiffusion = startDiffusion;
		this.pathToMusiqueFile = pathToMusiqueFile;
		this.endDiffusion = endDiffusion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDiffusion() {
		return startDiffusion;
	}
	public void setStartDiffusion(String startDiffusion) {
		this.startDiffusion = startDiffusion;
	}
	public String getPathToMusiqueFile() {
		return pathToMusiqueFile;
	}
	public void setPathToMusiqueFile(String pathToMusiqueFile) {
		this.pathToMusiqueFile = pathToMusiqueFile;
	}
	public String getEndDiffusion() {
		return endDiffusion;
	}
	public void setEndDiffusion(String endDiffusion) {
		this.endDiffusion = endDiffusion;
	}
}
