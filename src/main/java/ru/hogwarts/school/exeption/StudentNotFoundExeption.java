package ru.hogwarts.school.exeption;

public class StudentNotFoundExeption extends RuntimeException{
    public StudentNotFoundExeption() {
    }

    public StudentNotFoundExeption(String message) {
        super(message);
    }

    public StudentNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public StudentNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
