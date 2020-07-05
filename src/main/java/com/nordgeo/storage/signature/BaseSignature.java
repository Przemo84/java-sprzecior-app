package com.nordgeo.storage.signature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public abstract class BaseSignature {

    public boolean validate(InputStream stream, byte[] signature) throws IOException {
        byte[] magic = new byte[signature.length];
        int count = stream.read(magic);

        return count >= signature.length && Arrays.equals(signature, magic);
    }
}
