package com.intro.api.model;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    
    private static ThreadLocal<Integer> userId = new ThreadLocal<Integer>();

    public Integer getUserId() {
        return userId.get();
    }

    public void setUserId(Integer id) {
        userId.set(id);
    }

    public void clear() {
        userId.remove();
    }
}