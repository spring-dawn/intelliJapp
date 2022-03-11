package com.kh.app3_snapshot;

import com.kh.app3_snapshot.test.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Person person(){
        return new Person("홍길동",30);
    }
}
