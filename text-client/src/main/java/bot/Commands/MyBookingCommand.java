package bot.Commands;

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

    private final static Booking BOOKING = new Booking();

    @Override
    public void execute(Entity entity, String text) {
        for (BookingInformation bookingInformation : BOOKING.getBookingInformationList()){
            addMenuForText(entity, bookingInformation.toString(), bookingInformation.getMenu());
        }
    }

    @Override
    public String getCommandName() {
        return "/mybooking";
    }

    private void addMenuForText(Entity entity, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = new SendMessage()
                .setText(text)
                .setParseMode(ParseMode.HTML)
                .setReplyMarkup(replyKeyboard);
        entity.getMessages().execute(sendMessage);
    }
}
