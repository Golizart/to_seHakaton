package bot.Commands.BotCommand;

import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 08.12.17.
 */
public class SettingCommand implements Command {

    private  static SettingCommand settingsCommand;

    public static SettingCommand getInstance(){
        if(settingsCommand == null)
            settingsCommand = new SettingCommand();
        return settingsCommand;
    }

    @Override
    public void execute(Entity entity) {
        menu(entity);
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.SETTINGS;
    }


    private void menu(Entity entity){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("\uD83D\uDCCA Выбрать сортировку запросов");
        keyboardFirstRow.add("\uD83D\uDCF1 Вид отбражения результатов");
        keyboardFirstRow.add("\uD83D\uDCDC Отобразить все мои настройки");
        keyboardFirstRow.add("\u274C Закрыть меню");
        keyboardFirstRow.add("\u2B05 Назад");
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setText(CommandTypes.SETTINGS.getCommand());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        entity.getMessages().execute(sendMessage);
    }

}
