package com.musiqueetsi.backend.service;

import java.io.IOException;
import java.util.ArrayList;
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

    public void addArtistApplication(ArtistApplication application) throws IOException {
        List<ArtistApplication> list = this.getAllArtistApplication();
        list.add(application);
        jsonFileUtil.writeArtistApplication(list);
    }

    public boolean deleteArtistApplication(ArtistApplication application) throws IOException {
        List<ArtistApplication> list = this.getAllArtistApplication();
        if (list.contains(application)) {
            list.remove(application);
            jsonFileUtil.writeArtistApplication(list);
            return true;
        }
        return false;
    }

}
