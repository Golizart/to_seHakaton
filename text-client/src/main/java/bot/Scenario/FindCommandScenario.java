package bot.Scenario;

import bot.Commands.BotCommand.*;
import bot.Commands.BotCommand.Settings.PrintSettingCommand;
import bot.Commands.BotCommand.Settings.SortSettingCommand;
import bot.Commands.BotCommand.Settings.ViewSettingCommand;
import bot.botInterface.Messages;

/**
 * Created by golizar on 09.12.17.
 */
public class FindCommandScenario {

    public static Command findCommand(Messages messages){

        if(commandFind(messages, CommandTypes.MENU))
            return MenuCommand.getInstance();

        if(commandFind(messages, CommandTypes.START))
            return StartCommand.getInstance();

        if(commandFind(messages, CommandTypes.CALL))
            return CallCommand.getInstance();

        if(commandFind(messages, CommandTypes.REVIEWS))
            return ReviewsCoomand.getInstance();

        if(commandFind(messages, CommandTypes.MORE_PROPOSALS))
            return MoreProposalsCoomand.getInstance();

        if(commandFind(messages, CommandTypes.MY_BOOKINGS))
            return MoreProposalsCoomand.getInstance();

        if(commandFind(messages, CommandTypes.CLOSE_MENU))
            return CloseMenuCommand.getInstance();

        if(commandFind(messages, CommandTypes.SETTINGS))
            return CloseMenuCommand.getInstance();

        if(commandFind(messages, CommandTypes.PRINT_SETTINGS))
            return PrintSettingCommand.getInstance();

        if(commandFind(messages, CommandTypes.SORT_SETTINGS))
            return SortSettingCommand.getInstance();

        if(commandFind(messages, CommandTypes.VIEW_SETTINGS))
            return ViewSettingCommand.getInstance();

        if(commandFind(messages, CommandTypes.BOOKING))
            return BookingCommand.getInstance();


        return SpeechCommand.getInstance();
    }

    private static boolean commandFind(Messages messages, CommandTypes commandTypes){
        String text = messages.getText();
        for(String variant : commandTypes.getCommandVariant()){
            if(text.equals(variant))
                return true;
        }
        return false;
    }
}
