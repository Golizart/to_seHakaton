package bot.Commands.BotCommand;

import bot.Entity.Entity;

public interface Command {
    void execute(Entity entity);
    CommandTypes getCommandType();
}
