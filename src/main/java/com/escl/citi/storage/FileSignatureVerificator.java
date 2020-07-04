package com.escl.citi.storage;

import com.escl.citi.storage.signature.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileSignatureVerificator {

    private List<FileSignature> allowedSignatures = new ArrayList<>();

    public FileSignatureVerificator() {
        allowedSignatures.add(new JpegSignature());
        allowedSignatures.add(new PngSignature());
        allowedSignatures.add(new GifSignature());
        allowedSignatures.add(new BmpSignature());
    }

    public boolean verify(MultipartFile file) {
        try {
            return verify(file.getInputStream());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean verify(InputStream stream) {
        try {
            for (FileSignature fileSignature : allowedSignatures) {
                boolean isValid;

                isValid = fileSignature.isValid(stream);


                if (isValid)
                    return true;
            }

            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
