package core;

import config.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HlamoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        // Set variables
        String user_first_name = update.getMessage().getChat().getFirstName();
        String user_last_name = update.getMessage().getChat().getLastName();
        String userMessage = update.getMessage().getText();
        long user_id = update.getMessage().getChat().getId();
        long chat_id = update.getMessage().getChatId();

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

             if (userMessage.chars().allMatch( Character::isDigit )) {

                WillBeDate wd = new WillBeDate();
                String answer = wd.willDate(userMessage);

                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(answer);

                log(user_first_name, user_last_name, Long.toString(user_id), userMessage, answer);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if ((userMessage.toLowerCase().contains(".".toLowerCase()))) {
                //TODO расчет разницы до текущей и после текущей даты (добавить условие)
                try {

                    PeriodBeDate pd = new PeriodBeDate();
                    String answer = pd.periodDate(userMessage);

                    SendMessage message = new SendMessage()
                            .setChatId(chat_id)
                            .setText(answer);

                    log(user_first_name, user_last_name, Long.toString(user_id), userMessage, answer);     try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();

                }

            } else if (!(userMessage.chars().allMatch( Character::isDigit ))
                    && !(userMessage.toLowerCase().contains("/".toLowerCase()))
                    && !(userMessage.toLowerCase().contains(".".toLowerCase()))) {

                String messageSend = "Вы ввели: " + userMessage;
                SendMessage message = new SendMessage().setChatId(chat_id).setText(messageSend);
                String answer = userMessage;

                log(user_first_name, user_last_name, Long.toString(user_id), userMessage, answer);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            switch (userMessage) {

                case "/hello":
                    String message_text_hello = "Добро пожаловать, " + user_first_name + " " +
                            user_last_name +"!" + "\n" + "Введите целое число или дату (ГГГГ.ММ.ДД).";

                    SendMessage message_hello = new SendMessage()
                            .setChatId(chat_id)
                            .setText(message_text_hello);
                    String answer = userMessage;

                    log(user_first_name, user_last_name, Long.toString(user_id), userMessage, answer);
                    try {
                        execute(message_hello);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                default:

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
                ". (id = " + user_id + ")\n" + txt);
        System.out.println("Бот ответил:\n" + bot_answer);
    }
}
