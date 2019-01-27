package com.cargodelivery.util.impl;

import com.cargodelivery.util.PasswordEncryption;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionImpl implements PasswordEncryption {

    private final static Logger logger = Logger.getLogger(PasswordEncryptionImpl.class);

    private final MessageDigest md5;

    public PasswordEncryptionImpl() {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5 digest isn't found", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getEncryptedPassword(String password) {

        if (password == null) {
            return null;
        }

        byte[] passwordDigest = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();

        for (byte b : passwordDigest) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }
}
