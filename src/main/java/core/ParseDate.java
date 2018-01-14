package core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseDate {
    // TODO добавить проверку на значения dd.MM.yyyy.XXXX

    final static String DATE_FORMAT = "dd.MM.yyyy";

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat checkDataFormat = new SimpleDateFormat(DATE_FORMAT);
            checkDataFormat.setLenient(false);
            checkDataFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
