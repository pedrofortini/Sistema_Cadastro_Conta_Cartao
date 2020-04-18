package com.cadastro.cliente.api.application;

public interface MessageConstants {

    String MESSAGE_USER_NOT_FOUND = "Couldn't find User with login %s";
    String MESSAGE_MACHINE_NOT_FOUND = "Couldn't find Machine with id %s";
    String MESSAGE_USER_IS_NOT_ADMIN = "Attempt to assign non-admin user as admin of machine. User login: %s";
    String MESSAGE_ERROR_PERSISTING_MACHINE = "An error ocurred while persisting Machine %s";
    String MESSAGE_ERROR_PERSISTING_USER = "An error ocurred while persisting User %s";
    String MESSAGE_ERROR_PERSISTING_MACHINE_EVENT_LOG = "An error ocurred while persisting event log for Machine %s";
    String MESSAGE_ERROR_USER_ALREADY_HAVE_ACESS = "User %s already have acess to Machine %s";

}
