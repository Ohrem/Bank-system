package com.example.kurs6.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Router {
    public enum Type{
        FORWARD,
        REDIRECT
    }

    private String pagePath;
    private Type type;


    public Router() {
        this.type = Type.FORWARD;
    }

    public Router(String pagePath) {
        this.pagePath = pagePath;
        this.type = Type.FORWARD;
    }

    public Router(String pagePath, Type type) {
        this.pagePath = pagePath;
        this.type = type;
    }
}
