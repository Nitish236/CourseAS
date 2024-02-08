package com.as.CourseAS.controller;

import com.as.CourseAS.dto.CourseDto;
import com.as.CourseAS.exception.CourseNotFoundException;
import com.as.CourseAS.exception.InvalidCourseRequestException;
import com.as.CourseAS.model.Course;
import com.as.CourseAS.service.CourseCreatorService;
import com.as.CourseAS.service.CourseService;
import com.as.CourseAS.service.TeacherService;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseCreatorService courseCreatorService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, CourseCreatorService courseCreatorService, TeacherService teacherService) {
        this.courseService = courseService;
        this.courseCreatorService = courseCreatorService;
        this.teacherService = teacherService;
    }
    
    // Route to Get All the Courses 
    @GetMapping("/getAll")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    
    // Route to Get the Course Details 
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
    	// Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Get the roles of the authenticated user
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // Extract the role names
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        
        // Send Details based on the role
        if(roles.contains("ROLE_COURSE_CREATOR")) {
        	Course course = courseCreatorService.getCourseById(id)
                    .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " not found"));
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        else {
        	CourseDto course = courseService.getCourseById(id)
                    .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " not found"));
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }
    
    // Route to Create new Course 
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
    
    // Route to Update the Course Details
    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Course course = courseService.updateCourse(id, updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    
    // Route to Delete the Course 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course with ID " + id + " deleted successfully", HttpStatus.OK);
    }

    // Exception handling (Handling the exceptions)
    @ExceptionHandler({CourseNotFoundException.class, InvalidCourseRequestException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
