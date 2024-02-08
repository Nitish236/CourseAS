package com.as.CourseAS.dto;

import com.as.CourseAS.model.CourseType;
import com.as.CourseAS.model.LearnMode;

public interface CourseDto {

    Long getId();
    String getName();
    String getSubject();
    Integer getChapters();
    Integer getClasses();
    CourseType getType();
    LearnMode getLearnMode();
}
