package com.cargodelivery.validator;

import com.cargodelivery.exception.IncorrectInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    private static Pattern regexPattern;
    private static Matcher regMatcher;

    public static void validateEmailAddress(String emailAddress) throws IncorrectInputException {

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        if (!regexPattern.matcher(emailAddress).matches()) {
            throw new IncorrectInputException("Incorrectly input email");
        }
    }

    public static void validateMobileNumber(String mobileNumber) throws IncorrectInputException {
        regexPattern = Pattern.compile("^\\+[0-9]{2,3}+-[0-9]{10}$");
        if (!regexPattern.matcher(mobileNumber).matches()) {
            throw new IncorrectInputException("Incorrectly input mobile number");
        }

    }

    public static void validateText(String str) throws IncorrectInputException {
        regexPattern = Pattern.compile("[A-ZА-ЯІ][a-z-а-яії]{1,15}");
        if (!regexPattern.matcher(str).matches()) {
            throw new IncorrectInputException("Incorrectly input text");
        }
    }

//    public static void validateNumber(String str) throws IncorrectInputException {
//        regexPattern = Pattern.compile("[1-9][0-9]{0,15}");
//        if (!regexPattern.matcher(str).matches()) {
//            throw new IncorrectInputException("Incorrectly input number");
//        }
//    }
}
