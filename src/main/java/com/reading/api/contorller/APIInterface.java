package com.reading.api.contorller;

import java.io.IOException;
import java.util.Map;

public interface APIInterface<T> {

    public T connect(String path, Map<String, String> map) throws IOException;
}
