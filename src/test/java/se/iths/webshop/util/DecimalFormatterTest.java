package se.iths.webshop.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Depinder Kaur
 * @version 01.
 * <h2>DecimalFormatterTest</h2>
 * @date 2024-04-09
 */
class DecimalFormatterTest {

    @Test
    void formatToTwoDecimalPlaces() {
        double result1 = DecimalFormatter.formatToTwoDecimalPlaces(8.392819);
        double result2 = DecimalFormatter.formatToTwoDecimalPlaces(108.3213908);
        double result3 = DecimalFormatter.formatToTwoDecimalPlaces(89.658921);
        assertEquals(8.39, result1);
        assertEquals(108.32, result2);
        assertEquals(89.66, result3);
    }
}