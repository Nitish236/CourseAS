package com.as.CourseAS.service;

import com.as.CourseAS.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
	
	private final CourseRepository courseRepository;

    public TeacherService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
