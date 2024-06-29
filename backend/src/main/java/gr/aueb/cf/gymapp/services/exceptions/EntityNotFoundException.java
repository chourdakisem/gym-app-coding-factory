package gr.aueb.cf.gymapp.services.exceptions;

import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.User;

public class EntityNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super(entityClass.getSimpleName() + " with id " + id + " was not found");
    }

    public EntityNotFoundException(Class<?> entityClass, String variable, String location) {
        super(entityClass.getSimpleName() + " with " + variable + " " + location + " was not found");
    }

    public EntityNotFoundException(Class<?> entityClass, Class<?> classz, Long id) {
        super(entityClass.getSimpleName() + " with " + classz.getSimpleName().toLowerCase() +
                "Id " + id + " was not found");
    }
}
