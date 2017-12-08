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
public class PrintSettingCommand implements Command {

    private static ViewEnum STATE_VIEW = ViewEnum.VIEW_VERTICAL;
    private static SortEnum STATE_SORT = SortEnum.SORT_BY_DEFAULT;

    @Override
    public void execute(Entity entity, String text) {
        printSettings(entity, text);
    }

    @Override
    public String getCommandName() {
        return "/printSettings";
    }

       public void printSettings(Entity entity, String text) {

        SendMessage sendMessage = new SendMessage()
                                    .setText(viewPrint())
                                    .setParseMode(ParseMode.HTML);
        entity.getMessages().execute(sendMessage);
    }

    private String viewPrint(){
        return  "сортировка: " + STATE_SORT.getText() +"\n отображение: "  + STATE_VIEW.getText() +"\n";
    }
}
