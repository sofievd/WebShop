package se.iths.webshop.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDateFormatterTest {

    @Test
    void getFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024,04,10, 10, 10, 10);
        String format = CustomDateFormatter.getFormattedDateTime(dateTime);
        assertEquals("2024-04-10 10:10:10", format);
    }
}