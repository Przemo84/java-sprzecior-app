package com.nordgeo.storage.signature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class BmpSignature implements FileSignature {


    private byte[] hexSignatures = {0x42, 0x4D};

    @Override
    public boolean isValid(InputStream stream) throws IOException {
        byte[] magic = new byte[hexSignatures.length];
        int count = stream.read(magic);

        return count >= hexSignatures.length && Arrays.equals(hexSignatures, magic);
    }
}
