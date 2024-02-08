package com.as.CourseAS.repository;

import com.as.CourseAS.model.Course;
import com.as.CourseAS.dto.CourseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    @Query("SELECT c.id as id, c.name as name, c.subject as subject, c.chapters as chapters, c.classes as classes, c.type as type, c.learnMode as learnMode FROM Course c WHERE c.id = ?1")
    Optional<CourseDto> findCourseDtoById(Long id);

    @Query("SELECT c.id as id, c.name as name, c.subject as subject, c.chapters as chapters, c.classes as classes, c.type as type, c.learnMode as learnMode FROM Course c")
    List<CourseDto> findAllCourseDtos();
}
