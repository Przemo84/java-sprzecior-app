package com.escl.citi.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileNameGenerator {


    public String generate(MultipartFile file) {
        return UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }
}
