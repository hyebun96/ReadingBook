package com.reading.api.contorller;

import java.io.IOException;
import java.net.URI;

public interface APIInterface<T> {

    public T connect(URI uri, String method) throws IOException;
}
