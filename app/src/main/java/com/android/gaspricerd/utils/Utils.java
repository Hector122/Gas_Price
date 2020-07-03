package com.android.gaspricerd.utils;

/**
 * Class with utils methods.
 */
public class Utils {

    /***
     * Get the String price RD$ and remove the currency notation.
     *
     * @param value String value with DOP currency symbol.
     * @return Double without format.
     * @throws NumberFormatException
     */
    public static double getMoneyWithoutSpecialCharacter(String value) throws NumberFormatException {
        String moneyWithoutCharacter = new StringBuilder(value).replace(0, 3, "").toString();
        return Double.parseDouble(moneyWithoutCharacter);
    }
}
