package com.nordgeo.storage.signature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class JpegSignature implements FileSignature {


    private byte[] hexSignatures = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};

    @Override
    public boolean isValid(InputStream stream) throws IOException {
        byte[] magic = new byte[hexSignatures.length];
        int count = stream.read(magic);

        return count >= hexSignatures.length && Arrays.equals(hexSignatures, magic);
    }
}
