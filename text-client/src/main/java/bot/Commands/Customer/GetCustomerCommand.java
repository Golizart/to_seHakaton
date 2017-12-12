package bot.Commands.Customer;

import bot.Entity.Entity;
import bot.Entity.SettingsView;
import bot.botInterface.MessageType;
import bot.botInterface.Messages;

/**
 * Created by golizar on 09.12.17.
 */
public class GetCustomerCommand {
    public static Entity getCustomer(Messages message, MessageType messageType ){
        SettingsView settings = new SettingsView();
        return new Entity(settings, message, null, null);
    }
}
