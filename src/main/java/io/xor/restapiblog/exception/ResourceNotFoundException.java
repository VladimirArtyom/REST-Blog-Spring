package io.xor.restapiblog.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final String fieldName;
    private final long fieldValue;

    public ResourceNotFoundException(String resourceName,
                                     String fieldName,
                                     long fieldValue) {
       super(String.format("$s resource not found for field '%s' with value '%s' ", resourceName, fieldName, fieldValue));
       this.resourceName = resourceName;
       this.fieldName = fieldName;
       this.fieldValue = fieldValue;
    }

}
