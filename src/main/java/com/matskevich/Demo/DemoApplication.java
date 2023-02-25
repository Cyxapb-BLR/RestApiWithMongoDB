package com.matskevich.Demo;

import com.matskevich.Demo.models.Address;
import com.matskevich.Demo.models.Gender;
import com.matskevich.Demo.models.Student;
import com.matskevich.Demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository) {
        return args -> {
            Address address = new Address("USA", "Brooklyn", 11224);
            Student student = new Student(
                    "Vad",
                    "Matskevich",
                    "vad@gmail.com",
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths", "Java"),
                    BigDecimal.TEN,
                    LocalDateTime.now());

            repository.insert(student);
        };
    }
}
