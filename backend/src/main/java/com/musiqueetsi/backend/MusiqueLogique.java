package com.musiqueetsi.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.musiqueetsi.model.MusiqueProperties;

public class MusiqueLogique {
	private static final String pathMusiqueProperties = "src/main/resources/musiques/properties/";
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static MusiqueProperties getPropertiesById(int id) {
		return readFileProperties(id + ".json");
	}
	
	public static MusiqueProperties readFileProperties(String path) {
		InputStream file;
		try {
			file = new FileInputStream(pathMusiqueProperties + path);
		} catch (FileNotFoundException e1) {
			return null;
		}
		try {
			MusiqueProperties musiqueProperties = mapper.readValue(file, MusiqueProperties.class);
			return musiqueProperties;
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean writeProperties(MusiqueProperties properties) {
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		try {
			writer.writeValue(new File(pathMusiqueProperties + properties.getId() + ".json"), properties);
			return true;
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<MusiqueProperties> getMusiquePropertiesByName(String name){
		List<MusiqueProperties> properties = new ArrayList<MusiqueProperties>();
		File dir = new File(pathMusiqueProperties);
		File[] directoryListing = dir.listFiles();
		for (File file : directoryListing) {
			System.out.println(file.getPath());
			MusiqueProperties prop = readFileProperties(file.getName());
			if (prop.getName().equals(name)) properties.add(prop);
		}
		return properties;
	}
	
	public static void main(String[] args) {
		System.out.println("AAAA");
		System.out.println(getPropertiesById(0));
		MusiqueProperties prop = getPropertiesById(0);
		prop.setId(2);
		System.out.println(writeProperties(prop));
		System.out.println(getPropertiesById(1));
		System.out.println(getMusiquePropertiesByName("name"));
		System.out.println(getMusiquePropertiesByName("a"));
	}
}
