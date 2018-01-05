package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class BotConfig {

    public static final String CONFIGURATION_BOT_FILE = "./config/config.properties";

    private static String BOT_NAME;
    private static String BOT_TOKEN;
    private static String BOT_ID;

    public static void load () {
        Properties botSettings = new Properties();

        try (InputStream is = new FileInputStream(new File(CONFIGURATION_BOT_FILE))){
            botSettings.load(is);
            is.close();
            System.out.println("Конфигурация успешно загружена!");
        } catch (Exception e) {
            System.out.println("Конфигурация не загрузилась");
        }

        setBotName(botSettings.getProperty("BotName","BotName"));
        setBotToken(botSettings.getProperty("BotToken","BotToken"));
        setBotId(botSettings.getProperty("BotId","BotId"));

    }

    public static String getBotName() {
        return BOT_NAME;
    }

    public static void setBotName(String botName) {
        BOT_NAME = botName;
    }

    public static String getBotToken() {
        return BOT_TOKEN;
    }

    public static void setBotToken(String botToken) {
        BOT_TOKEN = botToken;
    }

    public static String getBotId() {
        return BOT_ID;
    }

    public static void setBotId(String botId) {
        BOT_ID = botId;
    }

}