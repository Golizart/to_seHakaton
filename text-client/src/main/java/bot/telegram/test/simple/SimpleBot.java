package bot.telegram.test.simple;

import bot.Commands.BotCommand.Command;
import bot.Commands.Customer.GetCustomerCommand;
import bot.Entity.Entity;
import bot.Scenario.FindCommandScenario;
import bot.botInterface.MessageType;
import bot.botInterface.TelegramMessages;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


public class SimpleBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "WhatIsItYouWant_bot";
    }

    @Override
    public String getBotToken() {
        return "402968864:AAE_gk5pbTIMuYRZm0yX5rx2XZlL-Ba3rVo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        TelegramMessages telegramMessages = new TelegramMessages (this, update);
        Command command = FindCommandScenario.findCommand(telegramMessages);
        Entity entity = GetCustomerCommand.getCustomer(telegramMessages, MessageType.TELEGRAM);
        command.execute(entity);
    }

}