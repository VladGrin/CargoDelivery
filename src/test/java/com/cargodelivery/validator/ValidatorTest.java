package com.cargodelivery.validator;

import com.cargodelivery.exception.IncorrectInputException;
import org.junit.Test;

public class ValidatorTest {

    //-----------------------------------------------------------------------------------------------
    // Name validator test

    @Test
    public void validName() throws IncorrectInputException {
        String name = "Александр";
        Validator.validateName(name);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNameNullThenIncorrectInputException() throws IncorrectInputException {
        String name = null;
        Validator.validateName(name);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNameStartsWithLowcaseThenIncorrectInputException() throws IncorrectInputException {
        String name = "александр";
        Validator.validateName(name);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNameContainsWrongSymbolsThenIncorrectInputException() throws IncorrectInputException {
        String name = "Александр!";
        Validator.validateName(name);
    }

    //-----------------------------------------------------------------------------------------------
    // Mobile number validator test

    @Test
    public void validMobileNumber() throws IncorrectInputException {
        String mobileNumber = "+380674171377";
        Validator.validateMobileNumber(mobileNumber);
    }


    @Test(expected = IncorrectInputException.class)
    public void whenMobileNumberNullThenIncorrectInputException() throws IncorrectInputException {
        String mobileNumber = null;
        Validator.validateMobileNumber(mobileNumber);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenMobileNumberContainsLetterThenIncorrectInputException() throws IncorrectInputException {
        String mobileNumber = "+380674173d69";
        Validator.validateMobileNumber(mobileNumber);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenMobileNumberStartsNotFromPlusThenIncorrectInputException() throws IncorrectInputException {
        String mobileNumber = "1380674171377";
        Validator.validateMobileNumber(mobileNumber);
    }

    //-------------------------------------------------------------------------------------------------------
    // Login / mail validator test

    @Test
    public void validMail() throws IncorrectInputException {
        String mail = "vlad5855051@gmail.com";
        Validator.validateEmailAddress(mail);
    }


    @Test(expected = IncorrectInputException.class)
    public void whenMailNullThenIncorrectInputException() throws IncorrectInputException {
        String mail = null;
        Validator.validateEmailAddress(mail);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenMailMissAmpersentThenIncorrectInputException() throws IncorrectInputException {
        String mail = "vlad5855051gmail.com";
        Validator.validateEmailAddress(mail);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenMailMissDotThenIncorrectInputException() throws IncorrectInputException {
        String mail = "vlad5855051@gmailcom";
        Validator.validateEmailAddress(mail);
    }

    //---------------------------------------------------------------------------------------------
    // Weight validato test

    @Test(expected = IncorrectInputException.class)
    public void whenWeightNullThenIncorrectInputException() throws IncorrectInputException {
        String weight = null;
        Validator.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTypeNullThenIncorrectInputException() throws IncorrectInputException {
        String weight = "1";
        Validator.validateWeight(weight, null);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNotNumberThenIncorrectInputException() throws IncorrectInputException {
        String weight = "45d";
        Validator.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNegativeNumberThenIncorrectInputException() throws IncorrectInputException {
        String weight = "-1000";
        Validator.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenZeroThenIncorrectInputException() throws IncorrectInputException {
        String weight = "0";
        Validator.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenFloatNumberThenIncorrectInputException() throws IncorrectInputException {
        String weight = "50.5";
        Validator.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberMoreThreeAndTypeDocumentThenIncorrectInputException() throws IncorrectInputException {
        String weight = "4";
        String type = "DOCUMENT";
        Validator.validateWeight(weight, type);
    }

    @Test
    public void validInputForDocumentType() throws IncorrectInputException {
        String weight = "3";
        String type = "DOCUMENT";
        Validator.validateWeight(weight, type);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberMoreFiftyAndTypeParselThenIncorrectInputException() throws IncorrectInputException {
        String weight = "51";
        String type = "PARSEL";
        Validator.validateWeight(weight, type);
    }

    @Test
    public void validInputForParselType() throws IncorrectInputException {
        String weight = "50";
        String type = "PARSEL";
        Validator.validateWeight(weight, type);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberMoreThirtyThousandAndTypeFreightThenIncorrectInputException() throws IncorrectInputException {
        String weight = "30001";
        String type = "FREIGHT";
        Validator.validateWeight(weight, type);
    }

    @Test
    public void validInputForFreightType() throws IncorrectInputException {
        String weight = "30000";
        String type = "FREIGHT";
        Validator.validateWeight(weight, type);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Text validator test

    @Test(expected = IncorrectInputException.class)
    public void whenTextContainsNumberThenIncorrectInputException() throws IncorrectInputException {
        String text = "Some5text";
        Validator.validateText(text);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTextNullThenIncorrectInputException() throws IncorrectInputException {
        String text = null;
        Validator.validateText(text);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTextMoreThenFortyLettersThenIncorrectInputException() throws IncorrectInputException {
        String text = "Sometexttexttexttexttexttexttexttexttextq";
        Validator.validateText(text);
    }

    @Test
    public void ValidText() throws IncorrectInputException {
        String text = "Sometext";
        Validator.validateText(text);
    }

    //-------------------------------------------------------------------------------------------------------------
    // Number validator test

    @Test(expected = IncorrectInputException.class)
    public void whenNumberNullThenIncorrectInputException() throws IncorrectInputException {
        String number = null;
        Validator.validateNumber(number);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenInputNegativeNumberThenIncorrectInputException() throws IncorrectInputException {
        String number = "-50";
        Validator.validateNumber(number);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenInputNotNumberThenIncorrectInputException() throws IncorrectInputException {
        String number = "50a";
        Validator.validateNumber(number);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenInputFloatNumberThenIncorrectInputException() throws IncorrectInputException {
        String number = "50.5";
        Validator.validateNumber(number);
    }

    @Test
    public void whenZeroThenValidInput() throws IncorrectInputException {
        String number = "0";
        Validator.validateNumber(number);
    }

    @Test
    public void validNumber() throws IncorrectInputException {
        String number = "1";
        Validator.validateNumber(number);
    }
}