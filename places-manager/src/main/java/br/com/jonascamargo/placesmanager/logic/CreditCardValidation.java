package br.com.jonascamargo.placesmanager.logic;

// Luhn algorithm for credit card validation - it doesn't provide entire validation for credit card, just part of them.

public class CreditCardValidation {
    public static boolean isCreditCardValid(String cardNumber) {
        String reversedString = invertString(cardNumber);
        int sumEven = 0, sumOdd = 0;
        int auxDigit;
        for (int position = 0; position < cardNumber.length(); position++) {
            auxDigit = Character.getNumericValue(reversedString.charAt(position));
            if(position % 2 != 0) {
                if((auxDigit * 2)  > 9){
                    sumOdd += ((auxDigit * 2) % 10) + 1;
                } else {
                    sumOdd += auxDigit * 2;
                }
            } else {
                sumEven += auxDigit;
            }
        }
        int sumEvenOdd = sumEven + sumOdd;
        if(sumEvenOdd % 10 == 0) 
            return true;
        return false;
    }


    public static String invertString(String cardNumber) {
        int length = cardNumber.length();
        StringBuilder reversedString = new StringBuilder(length);
        for (int position = length - 1; position >= 0; position--) {
            reversedString.append(cardNumber.charAt(position));
        }
        return reversedString.toString();
    }



}
