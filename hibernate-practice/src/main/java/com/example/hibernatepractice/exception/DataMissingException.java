package com.example.hibernatepractice.exception;

/**
 * Возникает в случае, если запрашиваемые данные отсутствуют в базе данных.
 */
public class DataMissingException extends RuntimeException {

    public DataMissingException(String message) {
        super(message);
    }

}
