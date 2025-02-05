package bo.edu.ucb.chatbot.config;

import bo.edu.ucb.chatbot.bot.FilmBotHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotConfig.class);
    @Bean
    public TelegramBotsApi telegramBotsApi(FilmBotHandler filmBotHandler) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(filmBotHandler);
            LOGGER.info("Bot registrado exitosamente.");
            return telegramBotsApi;
        } catch (TelegramApiException e) {
            LOGGER.error("Error al registrar el bot: ", e);
            throw new RuntimeException("Error al registrar el bot", e);
        }
    }
}