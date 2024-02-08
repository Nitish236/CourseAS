package com.as.CourseAS.service;

import com.as.CourseAS.exception.CourseNotFoundException;
import com.as.CourseAS.model.Course;
import com.as.CourseAS.repository.CourseRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CourseCreatorService {
	
	private final CourseRepository courseRepository;
	
    public CourseCreatorService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    // To Get the Course along with the TimeStamps
    public Optional<Course> getCourseById(Long id) {
    	Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new CourseNotFoundException("Course with id " + id + " not found");
        }
        
        return optionalCourse;
    }
}
