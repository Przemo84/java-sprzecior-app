package com.escl.citi.storage;

import com.escl.citi.exception.ImageWrongScaleException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    private FileSignatureVerificator signatureVerificator;

    @Autowired
    public FileSystemStorageService(StorageProperties properties, FileSignatureVerificator signatureVerificator) {
        this.rootLocation = Paths.get(properties.getLocation());

        this.signatureVerificator = signatureVerificator;
    }

    public String store(MultipartFile file, CroppedImageParams croppedImageParams) {

        if (croppedImageParams.getX().isEmpty() && croppedImageParams.getY().isEmpty()) {
            return store(file);
        }
        Integer x = Math.abs(Math.round(Float.valueOf(croppedImageParams.getX())));
        Integer y = Math.abs(Math.round(Float.valueOf(croppedImageParams.getY())));
        Integer width = Math.abs(Math.round(Float.valueOf(croppedImageParams.getWidth())));
        Integer height = Math.abs(Math.round(Float.valueOf(croppedImageParams.getHeight())));

        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            BufferedImage subImage = img.getSubimage(x, y, width, height);

            String filename = pathSanitize(file);

            String path = rootLocation + "/" + filename;


            ImageIO.write(subImage, "png", new File(path));

            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MultipartFile getExistingImageAsMultipartFile(String oldImageName) {
        String path = rootLocation + "/" + oldImageName;

        File existingImage = new File(path);
        MockMultipartFile multipartFile = null;

        try {
            FileInputStream existingImageStream = FileUtils.openInputStream(existingImage);

            multipartFile = new MockMultipartFile(oldImageName, oldImageName, null, existingImageStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return multipartFile;
    }

    @Override
    public String store(MultipartFile file) {
        String filename = pathSanitize(file);


        try {
            if (!signatureVerificator.verify(file)) {
                throw new StorageException("This file type is not allowed");
            }

            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }

            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);


            return filename;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public String store(InputStream inputStream, String fileName) {
        String filename = pathSanitize();

        try {
            if (!signatureVerificator.verify(inputStream)) {
                throw new StorageException("This file type is not allowed");
            }

            Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Path load(String filename) {
        filename = pathSanitize(filename);

        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        filename = pathSanitize(filename);

        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            if (!Files.exists(rootLocation))
                Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private String pathSanitize(String filename) throws StorageException {
        filename = StringUtils.cleanPath(filename);

        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                            + filename);
        }

        return filename;
    }

    private String pathSanitize(MultipartFile file) {
        return UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }

    private String pathSanitize() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getImageFileNameAndStore(MultipartFile file, CroppedImageParams croppedImageParams) throws ImageWrongScaleException {

        // TODO comment ration 3/4
//        if ((croppedImageParams.getWidth().isEmpty() || croppedImageParams.getHeight().isEmpty()) ||
//                (!croppedImageParams.getWidth().equals(croppedImageParams.getHeight())))
//            throw new ImageWrongScaleException();

        return store(file, croppedImageParams);
    }
}