package com.test;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.domain.Book;
import com.test.infraestructure.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CassandraApplication implements CommandLineRunner {

    @Autowired
    public transient BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(CassandraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Book> r = repository.findAll();
        r.forEach(c -> log.info(c.toString()));

        repository.save(Book.builder().author("Autor A").name("DB4Beginners").subject("Infoq Brasil")
                .title("MongoDB Atlas").id(UUID.randomUUID()).build());

        r = repository.findAll();
        r.forEach(c -> log.info(c.toString()));

    }
}
