package com.alumind.llm.exception;

public class FeedbackOperationException {
    public static class SpamDetectionException extends RuntimeException {
        public SpamDetectionException(String message) {
            super(message);
        }
    }

    public static class FeedbackProcessingException extends RuntimeException {
        public FeedbackProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class DatabaseOperationException extends RuntimeException {
        public DatabaseOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
