package bot.Entity;

import bot.Commands.BotCommand.Command;
import bot.botInterface.Messages;

/**
 * Created by golizar on 08.12.17.
 */
public class Entity {
    private String name;
    private String phone;
    private SettingsView settingsView;
    private Messages messages;
    private Command pastState;
    private Command nextState;

    public Entity(SettingsView settings, Messages messages, Command paststate, Command nextstate) {
        this.name = messages.getUserName();
        this.phone = messages.getUserPhoneNumber();
        this.settingsView = settings;
        this.messages = messages;
        this.pastState = paststate;
        this.nextState = nextstate;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public SettingsView getSettingsView() {
        return settingsView;
    }

    public Messages getMessages() {
        return messages;
    }

    public Command getPastState() {
        return pastState;
    }

    public Command getNextState() {
        return nextState;
    }
}
