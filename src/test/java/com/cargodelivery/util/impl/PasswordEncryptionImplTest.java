package com.cargodelivery.util.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncryptionImplTest {

    @Test
    public void getEncryptedPassword() {
        String password = "qwerty";
        PasswordEncryptionImpl encryption = new PasswordEncryptionImpl();
        String encryptedPassword = encryption.getEncryptedPassword(password);
        String expectedPassword = "d8578edf8458ce06fbc5bb76a58c5ca4";
        assertEquals(expectedPassword, encryptedPassword);
    }
}