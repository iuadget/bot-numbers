package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WillBeDate {

    public String willDate (String userMessage){
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.ENGLISH);
        int interval = Integer.parseInt(userMessage);

        String message_text_data = "Текущая дата: " + dateTimeFormatter.format(today) + "\n"
                + "Через " + userMessage + " дней " + "будет: " +
                dateTimeFormatter.format(today.plusDays(interval)) + "\n" +
                "Год " + today.plusDays(interval).getYear() + " будет высокосный? - " + today.isLeapYear() +
                "\n" + "День недели: " + today.plusDays(interval).getDayOfWeek();

        return message_text_data;
    }

}
