package com.teamsolutions.demoedmsocr.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ListingAssetsImpl implements ListingAssets {

    private final Cloudinary cloudinary;

    @Override
    public Map<String, Object> listAssets(String nextCursor) {
        try {
            return cloudinary.api().resources(ObjectUtils.asMap(
                    "type", "upload",
                    "prefix", nextCursor));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
