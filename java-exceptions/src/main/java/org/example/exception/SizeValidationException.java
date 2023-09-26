package org.example.exception;

public class SizeValidationException extends Exception {
    private final int maxSize;

    public SizeValidationException(int maxSize) {
        super("Превышен максимальный размер коллекции");
        this.maxSize = maxSize;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + maxSize;
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage() + ": " + maxSize;
    }

}
