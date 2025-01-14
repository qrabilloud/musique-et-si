package com.musiqueetsi.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiqueetsi.backend.model.ArtistApplication;
import com.musiqueetsi.backend.util.JsonFileUtil;

@Service
public class ArtistApplicationService {

    @Autowired
    private JsonFileUtil jsonFileUtil;

    public List<ArtistApplication> getAllArtistApplication() throws IOException {
        return jsonFileUtil.readArtistApplication();
    }

}
