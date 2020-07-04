package com.escl.citi.storage;

import com.escl.citi.exception.ImageWrongScaleException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    String store(MultipartFile file, CroppedImageParams imageParams);

    String store(InputStream inputStream, String fileName);

    MultipartFile getExistingImageAsMultipartFile(String oldImageName);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    String getImageFileNameAndStore(MultipartFile file, CroppedImageParams imageParams) throws ImageWrongScaleException;

}