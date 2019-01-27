package com.cargodelivery.validator;

import com.cargodelivery.exception.IncorrectInputException;

import java.util.regex.Pattern;

public class Validator {

    public static void validateName(String name) throws IncorrectInputException {
        if (name == null || !Pattern.compile("[A-ZА-ЯІ][a-z-а-яії ]{1,39}").matcher(name).matches()) {
            throw new IncorrectInputException("Incorrectly input name");
        }
    }

    public static void validateMobileNumber(String mobileNumber) throws IncorrectInputException {
        if (mobileNumber == null || !Pattern.compile("^\\+[0-9]{12,13}$").matcher(mobileNumber).matches()) {
            throw new IncorrectInputException("Incorrectly input mobile number");
        }
    }

    public static void validateEmailAddress(String emailAddress) throws IncorrectInputException {
        if (emailAddress == null || !Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$").matcher(emailAddress).matches()) {
            throw new IncorrectInputException("Incorrectly input email/login");
        }
    }

    public static void validateWeight(String weight, String type) throws IncorrectInputException {
        if (weight == null || type == null || !Pattern.compile("[1-9][0-9]{0,5}").matcher(weight).matches()) {
            throw new IncorrectInputException("Incorrectly input weight");
        }
        int weightInt = Integer.parseInt(weight);
        if ((weightInt > 3 && type.equals("DOCUMENT") ||
                (weightInt > 50 && type.equals("PARSEL")) ||
                (weightInt > 30000 && type.equals("FREIGHT")))) {
            throw new IncorrectInputException("Incorrectly input weight");
        }
    }

    public static void validateText(String text) throws IncorrectInputException {
        if (text == null || !Pattern.compile("[A-ZА-ЯІa-z-а-яії ]{1,40}").matcher(text).matches()) {
            throw new IncorrectInputException("Incorrectly input text");
        }
    }

    public static void validateNumber(String number) throws IncorrectInputException {
        if (number == null || !Pattern.compile("[0-9]{0,5}").matcher(number).matches()) {
            throw new IncorrectInputException("Incorrectly input number");
        }
    }
}
