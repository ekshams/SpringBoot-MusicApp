package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class  MusicApplication {

	public static void main(String[] args) {
		File f = new File("public/images");
		if(!f.isDirectory()){
			if(f.mkdirs()){
				System.out.println("public/images directory created!!!");
			}

		}
		SpringApplication.run(MusicApplication.class, args);
	}
}
