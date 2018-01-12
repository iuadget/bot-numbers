package core;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class PeriodBeDate {

    public String periodDate (String userMessage){

        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.ENGLISH);
        LocalDate userDate = LocalDate.parse(userMessage, format);
        Period p = Period.between(today, userDate);
        long p2 = ChronoUnit.DAYS.between(today, userDate);

        String answer = "Разница между датами:\n" +
                p.getYears() + " года(-ов),\n" +
                p.getMonths() + " месяца(-ов),\n" +
                p.getDays() + " дня(-ей).\n" +
                "всего " + p2 + " дня(-ей)";

        return answer;

    }
}
