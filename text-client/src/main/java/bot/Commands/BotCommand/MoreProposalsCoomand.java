package bot.Commands.BotCommand;

import bot.Entity.Entity;

/**
 * Created by golizar on 09.12.17.
 */
public class MoreProposalsCoomand implements Command{
    private  static MoreProposalsCoomand moreProposalsCommand;

    public static MoreProposalsCoomand getInstance(){
        if(moreProposalsCommand == null)
            moreProposalsCommand = new MoreProposalsCoomand();
        return moreProposalsCommand;
    }

    @Override
    public void execute(Entity entity) {

    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.MORE_PROPOSALS;
    }
}
