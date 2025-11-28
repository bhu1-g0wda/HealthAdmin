package healthadmin;

import java.time.*;
import java.time.format.DateTimeFormatter;

final class DateUtils {
    private DateUtils() {} // private constructor (utility class)

    static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}

