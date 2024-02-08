package com.as.CourseAS.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Course name is required")
    private String name;
    
    @NotBlank(message = "Course subject is required")
    private String subject;
    
    @Min(value = 1, message = "Number of chapters must be greater than 0")
    private int chapters;
    
    @Min(value = 1, message = "Number of classes must be greater than 0")
    private int classes;
    
    @NotNull(message = "Course type is required")
    private CourseType type;
    
    @NotNull(message = "Learn mode is required")
    private LearnMode learnMode;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Course() {
    }

    public Course(String name, String subject, int chapters, int classes, CourseType type, LearnMode learnMode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.subject = subject;
        this.chapters = chapters;
        this.classes = classes;
        this.type = type;
        this.learnMode = learnMode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }
    
    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }
    
    public CourseType getType() {
    	return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public LearnMode getLearnMode() {
        return learnMode;
    }

    public void setLearnMode(LearnMode learnMode) {
        this.learnMode = learnMode;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime created_At) {
        this.createdAt = created_At;
    }
 
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updated_At) {
        this.updatedAt = updated_At;
    }

    // toString method
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", chapters=" + chapters + '\'' +
                ", classes=" + classes + '\'' +
                ", type='" + type + '\'' +
                ", learnMode='" + learnMode + '\'' +
                '}';
    }
}
