package com.teamsolutions.demoedmsocr.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileDownloadImpl implements FileDownload{

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String getImageUrl(String publicId) {

//        return cloudinary.download().generate(publicId);
        return cloudinary.url().publicId(publicId).generate();

    }
}
