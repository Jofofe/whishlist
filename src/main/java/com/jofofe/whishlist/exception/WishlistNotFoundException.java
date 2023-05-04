package com.jofofe.whishlist.exception;

public class WishlistNotFoundException extends RuntimeException {

    public WishlistNotFoundException() {
        super("Wishlist não encontrada");
    }

}
