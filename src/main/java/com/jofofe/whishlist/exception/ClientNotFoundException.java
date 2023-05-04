package com.jofofe.whishlist.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Cliente não encontrado");
    }

}
