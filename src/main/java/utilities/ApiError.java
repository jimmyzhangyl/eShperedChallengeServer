package utilities;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Helper class for handleValidationExceptions.java
 * template from https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
 * @author Yuanlong Zhang
 * @date 11 Aug 2022
 */
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}
    
    
}
