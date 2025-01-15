package com.musiqueetsi.backend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.musiqueetsi.backend.model.MusiqueProperties;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@Service
public class MusiqueLogique {
	private static final String pathRessources = "src/main/resources/";
	private static final String pathMusique = pathRessources + "musiques/";
	private static final String pathMusiqueProperties = pathMusique + "properties/";
	private static final String[] endPathMusiqueFile = new String[] {"agiles/", "ephemeres/*/"};
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static MusiqueProperties getPropertiesById(int id) {
		return readFileProperties(id + ".json");
	}
	
	public static List<MusiqueProperties> getAllProperties(){
		List<MusiqueProperties> properties = new ArrayList<MusiqueProperties>();
		File dir = new File(pathMusiqueProperties);
		File[] directoryListing = dir.listFiles();
		for (File file : directoryListing) {
			MusiqueProperties prop = readFileProperties(file.getName());
			properties.add(prop);
		}
		return properties;
	}
	
	public static List<MusiqueProperties> getAllPropertiesPlayables(){
		String todayString = getTodayString();
		return getAllProperties().stream().
				filter(p -> p.getStartDiffusion().compareTo(todayString) <= 0 
							&& p.getEndDiffusion().compareTo(todayString) >= 0).toList();
	}
	
	private static String getTodayString() {
		ZoneId z = ZoneId.of( "Europe/Paris" );
		LocalDate today = LocalDate.now( z );
		String year = "" + today.getYear();
		String month = "" + today.getMonthValue();
		if (month.length() == 1) month = "0" + month;
		String day = "" + today.getDayOfMonth();
		if (day.length() == 1) day = "0" + day;
		return year + "-" + month + "-" + day;
	}
	
	private static MusiqueProperties readFileProperties(String path) {
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
		if (!verifyMusiquePath(properties.getPathToMusiqueFile())) return false;
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
		return getAllProperties().stream().
				filter(p -> p.getName().equals(name)).toList();
	}
	
	public static byte[] getMusicFile(MusiqueProperties musicInformations) {
		try {
		// We get the file 
		Path filePath = Paths.get(pathMusique + musicInformations.getPathToMusiqueFile() + musicInformations.getId() + ".mp4");
		
		//We convert it into byte[]
		byte[] musicBytes =  Files.readAllBytes(filePath);
		return musicBytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new byte[0];
		}
	}
	private static int generateId() {
		List<MusiqueProperties> allProperties = getAllProperties();
		if (allProperties.size() == 0) return 0;
		return 1 + allProperties.stream().max((final MusiqueProperties p1, final MusiqueProperties p2) -> p1.getId() - p2.getId()).get().getId();
	}
	
	public static boolean verifyMusiquePath(String musiquePath) {
		for (String possibleMP : endPathMusiqueFile) {
			if (possibleMP.equals(musiquePath))
				return true;
			String[] possibleMPSplit = possibleMP.split("/");
			String[] musiquePathSplit = musiquePath.split("/");
			if (possibleMPSplit.length == musiquePathSplit.length) {
				boolean possible = true;
				for (int i = 0; i < musiquePathSplit.length; i ++)
					if (!(possibleMPSplit[i].equals("*") || possibleMPSplit[i].equals(musiquePathSplit[i])))
						possible = false;
				if (possible) return true;
			}
		}
		return false;
	}
	
	public static String writeMusique(MultipartFile file, MusiqueProperties properties) {
		
		int id = generateId();
		properties.setId(id);
		if (!verifyMusiquePath(properties.getPathToMusiqueFile())) return "Failed to upload the file : the PathToMusiqueFile " + properties.getPathToMusiqueFile() + " is not allowed.";
		Path destinationFile = Paths.get(pathMusique + properties.getPathToMusiqueFile() + id);
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, destinationFile,
				StandardCopyOption.REPLACE_EXISTING);
			try {
				System.out.println(destinationFile.toString());
				FFmpeg ffmpeg = new FFmpeg(pathRessources + "ffmpeg.exe");
				FFprobe ffprobe = new FFprobe(pathRessources + "ffprobe.exe");
				FFmpegBuilder builder = new FFmpegBuilder()
						.setInput(destinationFile.toString())
						.overrideOutputFiles(true)
						.addOutput(destinationFile.toString() + ".mp4")
						.setFormat("mp4")
						.setAudioCodec("aac")
						.setAudioBitRate(4*32768)
						.disableVideo()
						.done();
				FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
				executor.createJob(builder).run();
				destinationFile.toFile().delete();
			} catch (IOException e1) {
				e1.printStackTrace();
				return "Error when trying to convert the file";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error while writing the file.";
		}
		writeProperties(properties);
		return "You successfully uploaded " + file.getOriginalFilename() + " (id : " + id + ") !";
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
		System.out.println(getAllPropertiesPlayables());
		System.out.println(getAllProperties());
		System.out.println(generateId());
	}
}