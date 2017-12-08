package bot.Commands;

import bot.Entity.Entity;

public interface Command {
    void execute(Entity entity, String text);
    String getCommandName();
}
