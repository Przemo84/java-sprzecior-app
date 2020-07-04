package com.escl.citi.storage.signature;

import java.io.IOException;
import java.io.InputStream;

public class PngSignature extends BaseSignature implements FileSignature {


    private byte[][] hexSignatures = {
            {0x47, 0x0D, 0x0A},
            {(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A},
            {(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A},
            {0x1A, 0x0A}
    };


    @Override
    public boolean isValid(InputStream stream) throws IOException {
        for (byte[] hexSignature : hexSignatures) {
            if (validate(stream, hexSignature))
                return true;
        }

        return false;
    }
}
