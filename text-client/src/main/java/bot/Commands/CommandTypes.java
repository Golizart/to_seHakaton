package bot.Commands;

/**
 * Created by golizar on 08.12.17.
 */
public enum CommandTypes {
    BOOKING("booking"),
    CLOSE_MENU("close menu"),
    MENU("menu"),
    MY_BOOKINGS("my bookings"),
    SETTINGS("settings"),
    START("start");

    private final String commandName;

    CommandTypes(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
