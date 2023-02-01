package com.example.kurs6.command;

import com.example.kurs6.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
