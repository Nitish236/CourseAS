package com.as.CourseAS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCourseRequestException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCourseRequestException(String message) {
        super(message);
    }

    public InvalidCourseRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
