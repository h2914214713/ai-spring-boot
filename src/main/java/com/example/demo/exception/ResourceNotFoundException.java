package com.example.demo.exception;

/**
 * 资源不存在异常。
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
