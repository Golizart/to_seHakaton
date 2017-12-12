package bot.Commands.BotCommand;

import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;



public class CloseMenuCommand implements Command {

    private  static CallCommand closeMenuCommand;

    public static CallCommand getInstance(){
        if(closeMenuCommand == null)
            closeMenuCommand = new CallCommand();
        return closeMenuCommand;
    }

    @Override
    public void execute(Entity entity) {
        SendMessage sendMessage = new SendMessage()
                                .setText("Для вызова меню воспользуйтесь командой /menu")
                                .setParseMode(ParseMode.HTML)
                                .setReplyMarkup(removeKeyBoard());
        entity.getMessages().execute(sendMessage);
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.CLOSE_MENU;
    }

    private ReplyKeyboardRemove removeKeyBoard(){
        ReplyKeyboardRemove replyKeyboardMarkup = new ReplyKeyboardRemove();
        return replyKeyboardMarkup;
    }
}
