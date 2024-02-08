package com.as.CourseAS.service;

import com.as.CourseAS.dto.CourseDto;
import com.as.CourseAS.exception.CourseNotFoundException;
import com.as.CourseAS.model.Course;
import com.as.CourseAS.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    // Gets all the Courses
    public List<CourseDto> getAllCourses() {
    	List<CourseDto> courses = courseRepository.findAllCourseDtos();
 
    	if(courses.isEmpty()) {
    		throw new CourseNotFoundException("No courses found");
    	}
    	return courses;
    }
    
    // To get the Course Details without the TimeStamps
    public Optional<CourseDto> getCourseById(Long id) {
    	Optional<CourseDto> course = courseRepository.findCourseDtoById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course with id " + id + " not found");
        }
        return course;
    }

    // To Create the Course
    public Course createCourse(Course course) {
    	
    	course.setCreatedAt(LocalDateTime.now());
    	course.setUpdatedAt(LocalDateTime.now());
    	
        return courseRepository.save(course);
    }
    
    // To Update the Course
    public Course updateCourse(Long id, Course updatedCourse) {
    	Optional<Course> course = courseRepository.findById(id);
    	if (course.isEmpty()) {
    	    throw new CourseNotFoundException("Course with id " + id + " not found");
    	}
    	
    	Course exisCourse = course.get();
    	
    	// Update only the details that have changed
    	if (updatedCourse.getName() != null) {
            exisCourse.setName(updatedCourse.getName());
        }
        if (updatedCourse.getSubject() != null) {
            exisCourse.setSubject(updatedCourse.getSubject());
        }
        if (updatedCourse.getChapters() != 0) {
            exisCourse.setChapters(updatedCourse.getChapters());
        }

        if (updatedCourse.getClasses() != 0) {
            exisCourse.setClasses(updatedCourse.getClasses());
        }

        if (updatedCourse.getType() != null) {
            exisCourse.setType(updatedCourse.getType());
        }

        if (updatedCourse.getLearnMode() != null) {
            exisCourse.setLearnMode(updatedCourse.getLearnMode());
        }
        
        exisCourse.setUpdatedAt(LocalDateTime.now());;
    	
        return courseRepository.save(exisCourse);
    }
    
    // Delete the Course
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course with id " + id + " not found");
        }
        courseRepository.deleteById(id);
    }
}
