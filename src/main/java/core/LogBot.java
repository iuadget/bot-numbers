package core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogBot {

    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Сообщение от " + first_name + " " + last_name +
                ". (id = " + user_id + ")\n" + txt);
        System.out.println("Бот ответил:\n" + bot_answer);
    }
}
