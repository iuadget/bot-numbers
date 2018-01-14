package core;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PeriodBeDate {

    public String periodDate (String userMessage){

        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate userDate = LocalDate.parse(userMessage, format);
        Period p = Period.between(today, userDate);
        long p2 = ChronoUnit.DAYS.between(today, userDate);
        String textYear,
                textMonth,
                textDay,
                textDayAll;

        if (p.getYears() == 1){
            textYear = " год, \n";
        } else if (p.getYears() > 1 & p.getYears() < 5){
            textYear = " года, \n";
        } else {
            textYear = " лет, \n";
        }

        if (p.getMonths() == 1){
            textMonth = " месяц, \n";
        } else if (p.getMonths() > 1 & p.getMonths() < 5){
            textMonth = " месяца, \n";
        } else {
            textMonth = " месяцев, \n";
        }

        if (p.getDays() == 1){
            textDay = " день, \n";
        } else if (p.getDays() > 1 & p.getDays() < 5){
            textDay = " дня, \n";
        } else {
            textDay = " дней, \n";
        }

        if (p2 == 1){
            textDayAll = " день.";
        } else if (p2 > 1 & p2 < 5){
            textDayAll = " дня.";
        } else {
            textDayAll = " дней.";
        }

        String answer = "Разница между датами:\n" +
                p.getYears() + textYear +
                p.getMonths() + textMonth +
                p.getDays() + textDay +
                "всего " + p2 + textDayAll;

        return answer;

    }
}
