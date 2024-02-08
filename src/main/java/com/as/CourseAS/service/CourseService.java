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

    public List<CourseDto> getAllCourses() {
    	List<CourseDto> courses = courseRepository.findAllCourseDtos();
    	
    	if(courses.isEmpty()) {
    		throw new CourseNotFoundException("No courses found");
    	}
    	
    	return courses;
    }

    public Optional<CourseDto> getCourseById(Long id) {
    	Optional<CourseDto> course = courseRepository.findCourseDtoById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course with id " + id + " not found");
        }
        
        return course;
    }


    public Course createCourse(Course course) {
    	
    	course.setCreatedAt(LocalDateTime.now());
    	course.setUpdatedAt(LocalDateTime.now());
    	
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
    	Optional<Course> course = courseRepository.findById(id);
    	if (course.isEmpty()) {
    	    throw new CourseNotFoundException("Course with id " + id + " not found");
    	}
    	
    	Course exisCourse = course.get();
    	
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

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            // Handle exception if course with given id does not exist
            throw new CourseNotFoundException("Course with id " + id + " not found");
        }
        courseRepository.deleteById(id);
    }
}
