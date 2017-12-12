package bot.Commands.BotCommand.Settings;

import bot.Commands.BotCommand.Command;
import bot.Commands.BotCommand.CommandTypes;
import bot.Entity.Entity;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;

/**
 * Created by golizar on 08.12.17.
 */
public class PrintSettingCommand implements Command {

    private  static PrintSettingCommand printSettingsCommand;

    public static PrintSettingCommand getInstance(){
        if(printSettingsCommand == null)
            printSettingsCommand = new PrintSettingCommand();
        return printSettingsCommand;
    }

    @Override
    public void execute(Entity entity) {
        printSettings(entity);
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.PRINT_SETTINGS;
    }

       public void printSettings(Entity entity) {

        SendMessage sendMessage = new SendMessage()
                                    .setText(viewPrint(entity))
                                    .setParseMode(ParseMode.HTML);
        entity.getMessages().execute(sendMessage);
    }

    private String viewPrint(Entity entity){
        return  "сортировка: " + entity.getSettingsView().getStateSort() +"\n отображение: "  + entity.getSettingsView().getStateView() +"\n";
    }
}
