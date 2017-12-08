package bot.botInterface;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by golizar on 07.12.17.
 */
public class TelegramMessages implements Messages {

    private final AbsSender absSender;
    private final Message message;

    public TelegramMessages(AbsSender absSender, Message message) {
        this.absSender = absSender;
        this.message = message;
    }

    @Override
    public void execute(SendMessage sendMessage) {
        try {
            sendMessage.setChatId(message.getChatId().toString());
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
