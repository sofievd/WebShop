package se.iths.webshop.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Depinder Kaur
 * @version <h2></h2>
 * @date 2024-03-27
 */
public class DecimalFormatter {

    public static double formatToTwoDecimalPlaces(double input) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        return Double.parseDouble(formatter.format(input));
    }
}
