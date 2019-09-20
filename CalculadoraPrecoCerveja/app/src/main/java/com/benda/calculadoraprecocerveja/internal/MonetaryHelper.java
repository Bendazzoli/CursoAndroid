package com.benda.calculadoraprecocerveja.internal;

import java.text.NumberFormat;
import java.util.Locale;

public class MonetaryHelper {

    private MonetaryHelper() {
    }

    public static String formatToMonetary(String value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String onlyNumbers = value.replaceAll("[^\\d]", "");
        Double normalizedValue = Double.valueOf(onlyNumbers) / 100;
        return formatter.format(normalizedValue);
    }

    public static String cleanFormat(String value) {
        StringBuilder stringBuilder = new StringBuilder(value.replaceAll("[^\\d]", ""));
        stringBuilder.insert(stringBuilder.length() - 2, ".");
        return stringBuilder.toString();
    }
}
