package bot.Commands.BotCommand.Settings;

import bot.Commands.BotCommand.Command;
import bot.Commands.BotCommand.CommandTypes;
import bot.Entity.Entity;
import bot.telegram.test.simple.ViewEnum;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 08.12.17.
 */
public class ViewSettingCommand implements Command {

    private  static ViewSettingCommand viewSettingsCommand;

    public static ViewSettingCommand getInstance(){
        if(viewSettingsCommand == null)
            viewSettingsCommand = new ViewSettingCommand();
        return viewSettingsCommand;
    }

    @Override
    public void execute(Entity entity) {
        viewSettings(entity);
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.VIEW_SETTINGS;
    }

    public void viewSettings(Entity entity) {
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
        sendMessage.setText(getCommandType().getCommand());
        sendMessage.setReplyMarkup(markupInline);
        entity.getMessages().execute(sendMessage);
    }
}
