package com.musiqueetsi.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiqueetsi.backend.model.MusicProposition;
import com.musiqueetsi.backend.util.JsonFileUtil;

@Service
public class MusicPropositionService {

    @Autowired
    private JsonFileUtil jsonFileUtil;

    public List<MusicProposition> getAllMusicProposition() throws IOException {
        System.out.println("In getAllMusicProposition");
        return jsonFileUtil.readMusicProposition();
    }

    public List<MusicProposition> getMusicProposition() throws IOException {
        List<MusicProposition> props = jsonFileUtil.readMusicProposition();
        List<MusicProposition> filteredProps = props.stream().filter(p -> p.getTheme().equals(""))
                .collect(Collectors.toList());
        return filteredProps;
    }

    public List<MusicProposition> getMusicPropositionByTheme(String theme) throws IOException {
        System.out.println(theme);
        List<MusicProposition> props = jsonFileUtil.readMusicProposition();
        List<MusicProposition> filteredProps = props.stream().filter(p -> p.getTheme().equals(theme))
                .collect(Collectors.toList());
        return filteredProps;
    }

    public void addMusicProposition(MusicProposition prop) throws IOException {
        List<MusicProposition> list = this.getAllMusicProposition();
        System.out.println("MusicPropositon lue");
        list.add(prop);
        System.out.println("Nouvelle liste:");
        for (MusicProposition p : list) {
            System.out.println(p.toString());
        }
        jsonFileUtil.writeMusicProposition(list);
    }

    public boolean deleteMusicPropsition(Long idMP) throws IOException {
        List<MusicProposition> answer = this.getAllMusicProposition();
        Optional<MusicProposition> result = answer.stream().filter(p -> p.getId() == idMP).findFirst();
        if (result.isPresent()) {
            answer.remove(result.get());
            jsonFileUtil.writeMusicProposition(answer);
            return true;
        } else {
            return false;
        }
    }

}
