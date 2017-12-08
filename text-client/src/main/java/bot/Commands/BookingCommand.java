package bot.Commands;

import bot.Entity.Entity;

/**
 * Created by golizar on 07.12.17.
 */
public class BookingCommand implements Command {

    @Override
    public void execute(Entity entity, String text) {

    }

    @Override
    public String getCommandName() {
        return "/booking";
    }
}
