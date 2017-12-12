package bot.botInterface;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;

/**
 * Created by golizar on 07.12.17.
 */

public interface Messages {
    void execute(SendMessage sendMessage);
    void sendPhoto(SendPhoto sendPhoto);
    String getUserPhoneNumber();
    String getUserName();
    Integer getUserId();
    String getText();
    String getData();
    Long getChatId();

}
