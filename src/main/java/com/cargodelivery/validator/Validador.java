package com.cargodelivery.validator;

import com.cargodelivery.exception.IncorrectInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    private static Pattern regexPattern;

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

    public static void validateText(String text) throws IncorrectInputException {
        if (text == null || !Pattern.compile("[A-ZА-ЯІa-z-а-яії]{1,40}").matcher(text).matches()) {
            throw new IncorrectInputException("Incorrectly input text");
        }
    }

    public static void validateWeight(String weight, String type) throws IncorrectInputException {
        if (weight == null || type == null || !Pattern.compile("[1-9][0-9]{0,5}").matcher(weight).matches()) {
            throw new IncorrectInputException("Incorrectly input weight");
        }
        int weightInt = Integer.parseInt(weight);
        if((weightInt > 3 && type.equals("DOCUMENT") ||
                (weightInt > 50 && type.equals("PARSEL")) ||
                (weightInt > 30000 && type.equals("FREIGHT")))){
            throw new IncorrectInputException("Incorrectly input weight");
        }
    }

    public static void validateNumber(String number) throws IncorrectInputException {
        if (number == null || !Pattern.compile("[0-9]{0,5}").matcher(number).matches()) {
            throw new IncorrectInputException("Incorrectly input number");
        }
    }
}
