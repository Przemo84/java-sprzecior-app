package com.escl.citi.storage.signature;

import java.io.IOException;
import java.io.InputStream;

public interface FileSignature {

    boolean isValid(InputStream stream) throws IOException;
}
