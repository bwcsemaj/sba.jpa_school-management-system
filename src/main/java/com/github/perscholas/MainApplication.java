package com.github.perscholas;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.config.JdbcConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainApplication {
    public static void main(String[] args) {
        JdbcConfigurator.initialize();
        EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();
        CourseRepository courseRepository = new CourseRepository(entityManagerFactory);
        StudentRepository studentRepository = new StudentRepository(entityManagerFactory, courseRepository);
        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        Runnable sms = new SchoolManagementSystem(studentService, courseService, entityManagerFactory);
        sms.run();
    }
}
