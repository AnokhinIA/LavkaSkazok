package ru.lavkaskazok.ls.service;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MainService {

    public String getImagePath(){
        Path path = Paths.get(System.getProperty("user.dir")).getParent();
        String imagePath;
        imagePath = path.toString() + "/images/";
        return imagePath;
    }
}
