package com.jeffhb60.backendshoppingcart.exception_handling;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectResourceNotFoundException extends RuntimeException {

    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ProjectResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s : '%s'", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ProjectResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s : '%s'", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
