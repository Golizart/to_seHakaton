package bot.botInterface;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by golizar on 07.12.17.
 */
public class TelegramMessages implements Messages {

    private final AbsSender absSender;
    private final Update update;
    private final Message message;

    public TelegramMessages(AbsSender absSender, Update update) {
        this.absSender = absSender;
        this.update = update;
        Message message = update.getMessage();
        CallbackQuery callbackQuery = update.getCallbackQuery();
        if (message != null)
            this.message = message;
        else
        if(callbackQuery != null && callbackQuery.getMessage() != null)
            this.message = callbackQuery.getMessage();
        else
            this.message = null;
    }


    @Override
    public void execute(SendMessage sendMessage) {
        if(message != null){
            try {
                sendMessage.setChatId(message.getChatId().toString());
                absSender.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void sendPhoto(SendPhoto sendPhoto) {
        try {
            sendPhoto.setChatId(message.getChatId().toString());
            absSender.sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserPhoneNumber() {
        return message.getContact().getPhoneNumber();
    }

    @Override
    public String getUserName() {
        return message.getContact().getFirstName();
    }

    @Override
    public Integer getUserId() {
        return message.getContact().getUserID();
    }

    @Override
    public String getText() {
        return message.getText();
    }

    @Override
    public String getData() {
        return update.getCallbackQuery().getData();
    }

    @Override
    public Long getChatId() {
        return message.getChatId();
    }
}
