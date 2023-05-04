package com.teamsolutions.demoedmsocr.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
//dependance Lombok pour gerer Getter/Setter, Constructor
public class OcrModel {

    private String DestinationLanguage;

    private MultipartFile Image;
}
