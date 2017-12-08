package bot.Commands.Settings;

import bot.Commands.Command;
import bot.Entity.Entity;
import bot.telegram.test.simple.SortEnum;
import bot.telegram.test.simple.ViewEnum;
import org.telegram.telegrambots.api.methods.ParseMode;
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
public class SortSettingCommand implements Command {

    @Override
    public void execute(Entity entity, String text) {
        sortSettings(entity, text);
    }

    @Override
    public String getCommandName() {
        return "/sortSettings";
    }

    private void sortSettings(Entity entity, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_DISTANCE.getText())
                .setCallbackData(SortEnum.SORT_BY_DISTANCE.getCommand()));
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_POPULARITY.getText())
                .setCallbackData(SortEnum.SORT_BY_POPULARITY.getCommand()));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_RATING.getText())
                .setCallbackData(SortEnum.SORT_BY_RATING.getCommand()));
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_DEFAULT.getText())
                .setCallbackData(SortEnum.SORT_BY_DEFAULT.getCommand()));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_PRICE.getText())
                .setCallbackData(SortEnum.SORT_BY_PRICE.getCommand()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markupInline);
        entity.getMessages().execute(sendMessage);
    }
}
