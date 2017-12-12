package bot.Commands.BotCommand;

import bot.Entity.Entity;

/**
 * Created by golizar on 07.12.17.
 */
public class BookingCommand implements Command {
    private  static BookingCommand bookingCommand;

    public static BookingCommand getInstance(){
        if(bookingCommand == null)
            bookingCommand = new BookingCommand();
        return bookingCommand;
    }

    @Override
    public void execute(Entity entity) {

    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.BOOKING;
    }
}
