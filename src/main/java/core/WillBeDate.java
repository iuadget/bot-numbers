package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class WillBeDate {

    public String formatDay (LocalDate date) {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .ofPattern("dd MMMM yyyy - EEEE")
                .withLocale(new Locale("ru", "RU"))
                .format(date);
    }

    public String willDate (String userMessage){
        LocalDate today = LocalDate.now();
        int interval = Integer.parseInt(userMessage);
        String textLeapYear;

        if (today.isLeapYear()) {
            textLeapYear = "Год " + today.plusDays(interval).getYear() + " будет високосный.";
        } else {
            textLeapYear = "Год " + today.plusDays(interval).getYear() + " будет не високосный.";
        }

        String answer = "Текущая дата: \n" + formatDay(today) + "\n"
                + "Через " + userMessage + " дня(-ей) " + "будет: \n" +
                formatDay(today.plusDays(interval)) + "\n" +
                textLeapYear;

        return answer;
    }

}
