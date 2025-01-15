package com.musiqueetsi.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean deleteArtistApplication(Long idApplication) throws IOException {
        List<ArtistApplication> answer = this.getAllArtistApplication();
        List<ArtistApplication> list = this.getAllArtistApplication();
        Optional<ArtistApplication> result = list.stream().filter(app -> app.getId() == idApplication).findFirst();
        if (result.isPresent()) {
            System.out.println("--------------");
            System.out.println(result.get());
            System.out.println("--------------");
            answer.remove(result.get());
            for (ArtistApplication app : answer) {
                System.out.println(app.toString());
            }
            jsonFileUtil.writeArtistApplication(answer);
            return true;
        }
        return false;
    }

}
