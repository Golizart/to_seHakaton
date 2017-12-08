package bot.Commands.Settings;

import bot.Commands.Command;
import bot.Entity.Entity;
import bot.telegram.test.simple.ViewEnum;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 08.12.17.
 */
public class ViewSettingCommand implements Command {

    @Override
    public void execute(Entity entity, String text) {
        viewSettings(entity, text);
    }

    @Override
    public String getCommandName() {
        return "/viewSettings";
    }

    public void viewSettings(Entity entity, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(ViewEnum.VIEW_HORIZONTAL.getText())
                .setCallbackData(ViewEnum.VIEW_HORIZONTAL.getCommand()));
        rowInline.add(new InlineKeyboardButton(ViewEnum.VIEW_VERTICAL.getText())
                .setCallbackData(ViewEnum.VIEW_VERTICAL.getCommand()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markupInline);
        entity.getMessages().execute(sendMessage);
    }
}
