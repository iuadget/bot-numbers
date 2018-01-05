package core;

import config.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class HlamoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        // Set variables
        String user_first_name = update.getMessage().getChat().getFirstName();
        String user_last_name = update.getMessage().getChat().getLastName();
        long user_id = update.getMessage().getChat().getId();
        long chat_id = update.getMessage().getChatId();

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();

            if (message_text.chars().allMatch( Character::isDigit )) {
                LocalDate today = LocalDate.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
                int interval = Integer.parseInt(update.getMessage().getText());

                String message_text_data = "Текущая дата: " + dateTimeFormatter.format(today) + ".\n"
                        + "через " + update.getMessage().getText() + " дней " + "будет: " +
                        dateTimeFormatter.format(today.plusDays(interval));
                String answer = message_text;

                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(message_text_data);

                log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if ((message_text.toLowerCase().contains(".".toLowerCase()))) {
                //TODO расчте разницы до текущей и после текущей даты
                try {
                    LocalDate today = LocalDate.now();

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd",Locale.ENGLISH);
                    LocalDate userDate = LocalDate.parse(message_text, format);

                    Period p = Period.between(today, userDate);
                    long p2 = ChronoUnit.DAYS.between(today, userDate);

                    String message_text_data = "Разница между датами " +
                            p.getYears() + " года(-ов), и " +
                            p.getMonths() + " месяца(-ов), и " +
                            p.getDays() + " дня(-ей). (" +
                            "всего " +p2 + " дня(-ей)";

                    SendMessage message = new SendMessage()
                            .setChatId(chat_id)
                            .setText(message_text_data);
                    String answer = message_text;

                    log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();

                }

            } else if (!(message_text.chars().allMatch( Character::isDigit ))
                    && !(message_text.toLowerCase().contains("/".toLowerCase()))
                    && !(message_text.toLowerCase().contains(".".toLowerCase()))) {

                String messageSend = "Вы ввели: " + message_text;
                SendMessage message = new SendMessage().setChatId(chat_id).setText(messageSend);
                String answer = message_text;

                log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            switch (message_text) {

                case "/hello":
                    String message_text_hello = "Добро пожаловать, " + user_first_name + " " +
                            user_last_name +"!" + "\n" + "Введите целое число или дату (ГГГГ.ММ.ДД).";

                    SendMessage message_hello = new SendMessage()
                            .setChatId(chat_id)
                            .setText(message_text_hello);
                    String answer = message_text;

                    log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
                    try {
                        execute(message_hello);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

            }

        }

    }

    @Override
    public String getBotUsername() {
        // Return bot username
        return BotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return BotConfig.getBotToken();
    }

    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Сообщение от " + first_name + " " + last_name +
                ". (id = " + user_id + ") \n Текст - " + txt);
        System.out.println("Бот ответил: \n Текст - " + bot_answer);
    }
}
