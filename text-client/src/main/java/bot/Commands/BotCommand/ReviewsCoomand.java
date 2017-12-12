package bot.Commands.BotCommand;

import bot.Entity.Entity;

/**
 * Created by golizar on 09.12.17.
 */
public class ReviewsCoomand implements Command{
    private  static ReviewsCoomand reviewsCommand;

    public static ReviewsCoomand getInstance(){
        if(reviewsCommand == null)
            reviewsCommand = new ReviewsCoomand();
        return reviewsCommand;
    }

    @Override
    public void execute(Entity entity) {

    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.REVIEWS;
    }
}
