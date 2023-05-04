package com.teamsolutions.demoedmsocr.controllers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.teamsolutions.demoedmsocr.models.OcrModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/api")
//Accepte toutes request provenant du Port 4200
@CrossOrigin(origins = "http://localhost:4200")
public class OcrController {

    @PostMapping("/ocr")
    //permet de chosir la langue sur le Frontend (Actuel French and Anglais)
    public ResponseEntity<String> DoOCR(@RequestParam("DestinationLanguage") String destinationLanguage, @RequestParam("Image") MultipartFile image) throws IOException {


        OcrModel request = new OcrModel();
        request.setDestinationLanguage(destinationLanguage);
        request.setImage(image);

        //Library(OCR) permettant de reconnaitre les Caractères sur l'image/ OCR
        ITesseract instance = new Tesseract();

        try {
            //Utilisation de la fonction Convert notre MultiPartFile en File
            BufferedImage in = ImageIO.read(convert(image));

            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = newImage.createGraphics();
            g.drawImage(in, 0, 0, null);
            g.dispose();

            //Langue de destination de Tessdata
            instance.setLanguage(request.getDestinationLanguage());
            instance.setDatapath("E:\\demo-edms-ocr\\tessdata");


            String result = instance.doOCR(newImage);

            return ResponseEntity.ok(result);

        } catch (TesseractException | IOException e) {
            System.err.println(e.getMessage());
            //Lorsque le doc est different de l'image
            return ResponseEntity.ok("Error while reading image");
        }

    }

    //La Librarie de OCR ne prend only les files et pour SpringBoot utilise les MultiPartFile, pour pouvoir utiliser les deux on convertit les MultiPartFile en File
    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
