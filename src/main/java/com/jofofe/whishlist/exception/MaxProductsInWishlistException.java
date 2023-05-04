package com.jofofe.whishlist.exception;

public class MaxProductsInWishlistException extends RuntimeException {

    public MaxProductsInWishlistException() {
        super("Wishlist já atigiu a quantidade maxima de 20 produtos");
    }

}
