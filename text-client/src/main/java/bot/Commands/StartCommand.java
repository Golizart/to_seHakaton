package bot.Commands;

import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 07.12.17.
 */
public class StartCommand implements Command{

    @Override
    public void execute(Entity entity, String text) {
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("Меню")
                .setCallbackData("/menu"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markupInline);
        entity.getMessages().execute(sendMessage);

    }

    @Override
    public String getCommandName() {
        return "/start";
    }
}
