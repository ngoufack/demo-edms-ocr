package com.teamsolutions.demoedmsocr.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class OcrModel {

    private String DestinationLanguage;

    private MultipartFile Image;
}
