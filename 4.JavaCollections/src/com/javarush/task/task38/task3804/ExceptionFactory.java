package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable getException(Enum exceptionMessage) {
        Throwable result = new IllegalArgumentException();
        if (exceptionMessage != null) {
            String message = exceptionMessage.name().substring(0, 1).toUpperCase()
                    .concat(exceptionMessage.name().substring(1).toLowerCase())
                    .replace('_', ' ');
            if (exceptionMessage.getClass() == ApplicationExceptionMessage.class) {
                result = new Exception(message);
            } else if (exceptionMessage.getClass() == DatabaseExceptionMessage.class) {
                result = new RuntimeException(message);
            } else if (exceptionMessage.getClass() == UserExceptionMessage.class) {
                result = new Error(message);
            }
        }
        return result;
    }
}