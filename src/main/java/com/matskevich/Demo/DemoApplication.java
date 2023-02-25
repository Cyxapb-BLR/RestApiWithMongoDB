package com.matskevich.Demo;

import com.matskevich.Demo.models.Address;
import com.matskevich.Demo.models.Gender;
import com.matskevich.Demo.models.Student;
import com.matskevich.Demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address("USA", "Brooklyn", 11224);

            String email = "vad@gmail.com";

            Student student = new Student(
                    "Vad",
                    "Matskevich",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths", "Java"),
                    BigDecimal.TEN,
                    LocalDateTime.now());

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));

            List<Student> students = mongoTemplate.find(query, Student.class);

            if (students.size() > 1) {
                throw new IllegalStateException("Found many students with email " + email);
            }
            if (students.isEmpty()) {
                System.out.println("Inserting student " + student);
                repository.insert(student);
            } else {
                System.out.println(student + " already exists");
            }
        };
    }
}
