package core;

import config.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class HlamoBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        // Set variables
        String user_first_name = update.getMessage().getChat().getFirstName();
        String user_last_name = update.getMessage().getChat().getLastName();
        long user_id = update.getMessage().getChat().getId();
        long chat_id = update.getMessage().getChatId();

//        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text_hello = "Добро пожаловать, " + user_first_name + "!" + "\n"
                    + "Введите число:";

            SendMessage message_hello = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text_hello);
            try {
                execute(message_hello); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
//        }

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

                LocalDate today = LocalDate.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
                int interval = Integer.parseInt(update.getMessage().getText());

                String message_text = "Текущая дата: " + dateTimeFormatter.format(today) + ".\n"
                        + "через " + update.getMessage().getText() + " дней " + "будет: " +
                        dateTimeFormatter.format(today.plusDays(interval));
                String answer = message_text;

                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);

                log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
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
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
}
