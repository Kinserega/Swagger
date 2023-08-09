package ru.hogwarts.school.exeption;

public class FacultyNotFoundExeption extends RuntimeException{
    public FacultyNotFoundExeption() {
    }

    public FacultyNotFoundExeption(String message) {
        super(message);
    }

    public FacultyNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public FacultyNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public FacultyNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
