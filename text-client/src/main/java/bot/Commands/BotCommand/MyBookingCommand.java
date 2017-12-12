package bot.Commands.BotCommand;

import bot.Entity.Entity;
import bot.telegram.test.simple.Booking;
import bot.telegram.test.simple.BookingInformation;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Created by golizar on 07.12.17.
 */
public class MyBookingCommand implements Command {


    private  static CallCommand callCommand;

    public static CallCommand getInstance(){
        if(callCommand == null)
            callCommand = new CallCommand();
        return callCommand;
    }

    private final static Booking BOOKING = new Booking();

    @Override
    public void execute(Entity entity) {
        for (BookingInformation bookingInformation : BOOKING.getBookingInformationList()){
            addMenuForText(entity, bookingInformation.toString(), bookingInformation.getMenu());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.MY_BOOKINGS;
    }

    private void addMenuForText(Entity entity, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = new SendMessage()
                .setText(text)
                .setParseMode(ParseMode.HTML)
                .setReplyMarkup(replyKeyboard);
        entity.getMessages().execute(sendMessage);
    }
}
