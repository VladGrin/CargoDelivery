package com.cargodelivery.validator;

import com.cargodelivery.exception.IncorrectInputException;
import org.junit.Test;

public class ValidadorTest {

    @Test(expected = IncorrectInputException.class)
    public void whenWeightNullThenIncorrectInputException() throws IncorrectInputException {
        String weight = null;
        Validador.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTypeNullThenIncorrectInputException() throws IncorrectInputException {
        String weight = "1";
        Validador.validateWeight(weight, null);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNotNumberThenIncorrectInputException() throws IncorrectInputException {
        String weight = "45d";
        Validador.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNegativeNumberThenIncorrectInputException() throws IncorrectInputException {
        String weight = "-1000";
        Validador.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenZeroThenIncorrectInputException() throws IncorrectInputException {
        String weight = "0";
        Validador.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenFloatNumberThenIncorrectInputException() throws IncorrectInputException {
        String weight = "50.5";
        Validador.validateWeight(weight, "type");
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberMoreThreeAndTypeDocumentThenIncorrectInputException() throws IncorrectInputException {
        String weight = "4";
        String type = "DOCUMENT";
        Validador.validateWeight(weight, type);
    }

    @Test
    public void validInputForDocumentType() throws IncorrectInputException {
        String weight = "3";
        String type = "DOCUMENT";
        Validador.validateWeight(weight, type);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberMoreFiftyAndTypeParselThenIncorrectInputException() throws IncorrectInputException {
        String weight = "51";
        String type = "PARSEL";
        Validador.validateWeight(weight, type);
    }

    @Test
    public void validInputForParselType() throws IncorrectInputException {
        String weight = "50";
        String type = "PARSEL";
        Validador.validateWeight(weight, type);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberMoreThirtyThousandAndTypeFreightThenIncorrectInputException() throws IncorrectInputException {
        String weight = "30001";
        String type = "FREIGHT";
        Validador.validateWeight(weight, type);
    }

    @Test
    public void validInputForFreightType() throws IncorrectInputException {
        String weight = "30000";
        String type = "FREIGHT";
        Validador.validateWeight(weight, type);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTextContainsNumberThenIncorrectInputException() throws IncorrectInputException {
        String text = "Some5text";
        Validador.validateText(text);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTextNullThenIncorrectInputException() throws IncorrectInputException {
        String text = null;
        Validador.validateText(text);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenTextMoreThenFortyLettersThenIncorrectInputException() throws IncorrectInputException {
        String text = "Sometexttexttexttexttexttexttexttexttextq";
        Validador.validateText(text);
    }

    @Test
    public void ValidText() throws IncorrectInputException {
        String text = "Sometext";
        Validador.validateText(text);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenNumberNullThenIncorrectInputException() throws IncorrectInputException {
        String number = null;
        Validador.validateNumber(number);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenInputNegativeNumberThenIncorrectInputException() throws IncorrectInputException {
        String number = "-50";
        Validador.validateNumber(number);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenInputNotNumberThenIncorrectInputException() throws IncorrectInputException {
        String number = "50a";
        Validador.validateNumber(number);
    }

    @Test(expected = IncorrectInputException.class)
    public void whenInputFloatNumberThenIncorrectInputException() throws IncorrectInputException {
        String number = "50.5";
        Validador.validateNumber(number);
    }

    @Test
    public void whenZeroThenValidInput() throws IncorrectInputException {
        String number = "0";
        Validador.validateNumber(number);
    }

    @Test
    public void validNumber() throws IncorrectInputException {
        String number = "1";
        Validador.validateNumber(number);
    }
}