package se.iths.webshop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CustomDateFormatter</h2>
 * @date 2024-03-28
 */
public class CustomDateFormatter {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getFormattedDateTime(LocalDateTime dateTime) {
        return FORMATTER.format(dateTime);
    }
}
