package se.iths.webshop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>DecimalFormatter</h2>
 * @date 2024-03-27
 */
public class DecimalFormatter {

    public static String formatToTwoDecimalPlaces(double input) {
        BigDecimal rounded = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        return rounded.toString();
    }
}
