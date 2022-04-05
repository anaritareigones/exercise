package com.jumia.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;


@SpringBootApplication
public class SpringBootAndSqLite3ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndSqLite3ExerciseApplication.class, args);
		Connection c = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:.\\sample.db");

		} catch (Exception e) {
			System.out.println("Exception catched  :  "+e);
		}
	}
}
