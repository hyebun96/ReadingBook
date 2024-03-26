package com.reading.api.contorller;

import org.springframework.http.RequestEntity;

import java.io.IOException;
import java.net.URI;

public interface APIInterface<T> {

    RequestEntity<Void> getMethodRequestEntity(URI uri) throws IOException;

    RequestEntity<Void> postMethodRequestEntity(URI uri) throws IOException;

    T connect(RequestEntity<Void> req) throws IOException;
}
