package bot.Commands.BotCommand.Settings;

import bot.Commands.BotCommand.Command;
import bot.Commands.BotCommand.CommandTypes;
import bot.Entity.Entity;
import bot.telegram.test.simple.SortEnum;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 08.12.17.
 */
public class SortSettingCommand implements Command {

    private  static SortSettingCommand sortSettingsCommand;

    public static SortSettingCommand getInstance(){
        if(sortSettingsCommand == null)
            sortSettingsCommand = new SortSettingCommand();
        return sortSettingsCommand;
    }

    @Override
    public void execute(Entity entity) {
        sortSettings(entity);
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.SORT_SETTINGS;
    }

    private void sortSettings(Entity entity){
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
        sendMessage.setText(getCommandType().getCommand());
        sendMessage.setReplyMarkup(markupInline);
        entity.getMessages().execute(sendMessage);
    }
}
