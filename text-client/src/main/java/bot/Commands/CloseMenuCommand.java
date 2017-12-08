package bot.Commands;

import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;



public class CloseMenuCommand implements Command {

    @Override
    public void execute(Entity entity, String text) {
        SendMessage sendMessage = new SendMessage()
                                .setText("Для вызова меню воспользуйтесь командой /menu")
                                .setParseMode(ParseMode.HTML)
                                .setReplyMarkup(removeKeyBoard());
        entity.getMessages().execute(sendMessage);
    }

    @Override
    public String getCommandName() {
        return "/closemenu";
    }

    private ReplyKeyboardRemove removeKeyBoard(){
        ReplyKeyboardRemove replyKeyboardMarkup = new ReplyKeyboardRemove();
        return replyKeyboardMarkup;
    }
}
