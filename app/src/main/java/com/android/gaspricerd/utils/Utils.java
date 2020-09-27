package com.android.gaspricerd.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Class with utils methods.
 */
public class Utils {
    public static final String TAG = "Utils.Class";

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

    /**
     * Converts the contents of an InputStream to a String.
     */
    public static String readStream(InputStream stream) {
        Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();

        try {
            String line;
            while ((line = in.readLine()) != null) {
                //Find the token hash
                if (line.contains("ArtDataChartDefinition13")) {
                    String[] values = line.split("=");
                    String jsonObject = values[1];
                    builder.append(jsonObject.substring(0, jsonObject.length() - 1));
                    break;
                }
            }

        } catch (IOException exception) {
            Log.e(TAG,exception.getMessage());
        }

        return builder.toString();
    }
}
