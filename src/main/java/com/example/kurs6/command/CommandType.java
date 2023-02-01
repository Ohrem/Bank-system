package com.example.kurs6.command;

import com.example.kurs6.command.impl.*;
import com.example.kurs6.command.impl.client.*;
import com.example.kurs6.command.impl.credit.*;
import com.example.kurs6.command.impl.deposit.*;

public enum CommandType {

    DEFAULT(new DefaultCommand()),
    ADD_NEW_CLIENT(new AddClientCommand()),
    DELETE_CLIENT_BY_ID(new DeleteClientCommand()),
    FIND_ALL_USERS(new FindAllClientsCommand()),
    TO_EDIT_USER(new ToEditClientCommand()),
    UPDATE_USER(new UpdateClientCommand()),
    TO_ADD_CLIENT(new ToAddClientPageCommand()),
    TO_BANK_DAY_CLOSING(new ToBankDayClosing()),

    //DEPOSITS

    TO_CREATE_NEW_DEPOSIT_PAGE(new ToCreateDepositPageCommand()),
    ADD_DEPOSIT(new CreateDepositCommand()),
    TO_CLIENT_DEPOSITS(new ToClientDepositsCommand()),
    SHOW_ALL_DEPOSITS(new ToAllDepositsPageCommand()),

    DELETE_DEPOSIT(new DeleteDepositCommand()),
    TO_EDIT_DEPOSIT(new ToEditDepositPageCommand()),
    EDIT_DEPOSIT(new UpdateDepositCommand()),
    UPDATE_DEPOSIT(new UpdateDepositCommand()),

    TO_CREATE_NEW_CREDIT_PAGE(new ToCreateCreditPageCommand()),
    ADD_CREDIT(new CreateCreditCommand()),
    TO_CLIENT_CREDITS(new ToClientCreditsCommand()),
    SHOW_ALL_CREDITS(new ToAllCreditsPageCommand()),
    TO_EDIT_CREDIT(new ToEditCreditPageCommand()),
    EDIT_CREDIT(new UpdateCreditCommand()),
    DELETE_CREDIT(new DeleteCreditCommand()),
    ;


    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    Command getCommand() {
        return command;
    }
}
