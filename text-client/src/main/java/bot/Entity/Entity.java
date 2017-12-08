package bot.Entity;

import bot.Commands.Command;
import bot.botInterface.Messages;

/**
 * Created by golizar on 08.12.17.
 */
public class Entity {
    private String name;
    private int phone;
    private SettingsView settingsView;
    private Messages messages;
    private Command pastState;
    private Command nextState;

    public Entity(String name, int phone, SettingsView settings, Messages messages, Command paststate, Command nextstate) {
        this.name = name;
        this.phone = phone;
        this.settingsView = settings;
        this.messages = messages;
        this.pastState = paststate;
        this.nextState = nextstate;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
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
