package bot.Commands;

import bot.Entity.Entity;
import bot.telegram.test.simple.SortEnum;
import bot.telegram.test.simple.ViewEnum;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 08.12.17.
 */
public class SettingCommand implements Command {

    @Override
    public void execute(Entity entity, String text) {
        menu(entity, text);
    }

    @Override
    public String getCommandName() {
        return "/settings";
    }


    private void menu(Entity entity, String text){
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
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        entity.getMessages().execute(sendMessage);
    }

}
