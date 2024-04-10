package se.iths.webshop.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CustomDateFormatterTest {

    @Test
    void getFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024,04,10, 10, 10, 10);
        String format = CustomDateFormatter.getFormattedDateTime(dateTime);
        assertEquals("2024-04-10 10:10:10", format);
    }
}