package com.matskevich.Demo.repositories;

import com.matskevich.Demo.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
