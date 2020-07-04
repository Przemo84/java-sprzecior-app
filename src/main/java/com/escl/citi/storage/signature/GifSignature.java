package com.escl.citi.storage.signature;

import java.io.IOException;
import java.io.InputStream;

public class GifSignature extends BaseSignature implements FileSignature {


    private byte[][] hexSignatures = {
            {0x47, 0x49, 0x46, 0x38, 0x37, 0x61},
            {0x47, 0x49, 0x46, 0x38, 0x39, 0x61},
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
