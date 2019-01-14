package com.cargodelivery.util.impl;

import com.cargodelivery.util.PasswordEncryption;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionImpl implements PasswordEncryption {

    private final static Logger logger = Logger.getLogger(PasswordEncryptionImpl.class);

    @Override
    public String getEncryptedPassword(String password) {
        if (password == null) {
            return null;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Encryption was not successful");
        }
        StringBuilder builder = null;
        if (md5 != null) {
            byte[] bytes = md5.digest(password.getBytes());
            builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
        }
        if (builder != null) {
            return builder.toString();
        } else {
            throw new RuntimeException();
        }
    }
}
