package bot.Commands.BotCommand;

import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


public class MenuCommand implements Command {
    private static MenuCommand menuCommand;
    public static MenuCommand getInstance(){
        if(menuCommand == null)
            menuCommand = new MenuCommand();
        return menuCommand;
    }


    @Override
    public void execute(Entity entity) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("\uD83D\uDCB3 Сделать заказ");
        keyboardFirstRow.add("\uD83D\uDED2 Мои заказы");
        keyboardFirstRow.add("\uD83C\uDF9B️️ Мои настройки");
        keyboardFirstRow.add("\u274C Закрыть меню");
        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setText(getCommandType().getCommand());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        entity.getMessages().execute(sendMessage);
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.MENU;
    }
}
