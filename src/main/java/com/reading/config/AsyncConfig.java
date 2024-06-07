package com.reading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {

    @Bean
    public ExecutorService excutorService() {
        //쓰레드 풀에서 재활용할 스레드를 가져옵니다.
        // TODO newCachedThreadPool 외에는 어떤것이 있을까?
        //  return Executors.newCachedThreadPool();
        return Executors.newFixedThreadPool(15);

    }
}
