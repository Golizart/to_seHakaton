package bot.Commands.BotCommand;

import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golizar on 07.12.17.
 */
public class StartCommand implements Command{
    private  static StartCommand startCommand;

    public static StartCommand getInstance(){
        if(startCommand == null)
            startCommand = new StartCommand();
        return startCommand;
    }

    @Override
    public void execute(Entity entity) {
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("Меню")
                .setCallbackData("/menu"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setText("Добрый день, этот бот вам поможет заказать лучшие цветы в Краснодаре! \n" +
                            "Введите запрос или воспользуйтесь кнопкой меню");
        sendMessage.setReplyMarkup(markupInline);
        entity.getMessages().execute(sendMessage);

    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.START;
    }
}
