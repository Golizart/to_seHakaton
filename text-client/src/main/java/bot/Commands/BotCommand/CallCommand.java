package bot.Commands.BotCommand;

import bot.Entity.Entity;

/**
 * Created by golizar on 09.12.17.
 */
public class CallCommand implements Command{
    private  static CallCommand callCommand;

    public static CallCommand getInstance(){
        if(callCommand == null)
            callCommand = new CallCommand();
        return callCommand;
    }

    @Override
    public void execute(Entity entity) {

    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.CALL;
    }
}
