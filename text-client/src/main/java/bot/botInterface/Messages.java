package bot.botInterface;

import org.telegram.telegrambots.api.methods.send.SendMessage;

/**
 * Created by golizar on 07.12.17.
 */
public interface Messages {
    void execute(SendMessage sendMessage);
}
