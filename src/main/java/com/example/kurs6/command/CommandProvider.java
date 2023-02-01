package com.example.kurs6.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private CommandProvider() {
    }

    public static Command defineCommand(HttpServletRequest request) {
        String command = request.getParameter(ParameterAndAttribute.COMMAND);

        if (command == null) {
            logger.info( "command is empty");
            return CommandType.DEFAULT.getCommand();
        }

        try {
            logger.info("in try CommandProvider");
            return CommandType.valueOf(command.toUpperCase()).getCommand();

        } catch (IllegalArgumentException exception) {
            logger.error( "command unknown command");
            return CommandType.DEFAULT.getCommand();
        }
    }
}
