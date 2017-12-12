package bot.Commands.Shop;

import bot.Buisnes.Shop;
import bot.Buisnes.ShopProposal;
import bot.Commands.BotCommand.CommandTypes;
import bot.Entity.Entity;
import bot.botInterface.Messages;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by golizar on 09.12.17.
 */
public class ViewShopResultCommand{
    private  static ViewShopResultCommand viewShopCommand;

    public static ViewShopResultCommand getInstance(){
        if(viewShopCommand == null)
            viewShopCommand = new ViewShopResultCommand();
        return viewShopCommand;
    }


    public void execute(Entity entity, HashMap<Long, JsonElement> proposals) {
        for (Map.Entry<Long, JsonElement> entry : proposals.entrySet())
        {
            Gson gson = new Gson();
            HashMap<String, Object> arr= gson.fromJson(entry.getValue(), HashMap.class);
            ArrayList<String> images = (ArrayList<String>) arr.get("images");
            String text = (String) arr.get("message");
            Shop shop = new Shop(1, "OOO'Рога и Копыта'", "г.Краснодар. ул Красная 123", "myShop.ru");
            ShopProposal shopProposal = new ShopProposal(text, shop, images);
            viewProposal(entity.getMessages(), shopProposal);
        }
    }


    public CommandTypes getCommandType() {
        return CommandTypes.VIEW_SHOP_RESULT;
    }


    private void viewProposal(Messages message, ShopProposal shopProposal) {
            printHeader(message);
            if(shopProposal.getImages().isEmpty())
                printPhoto(message, shopProposal.getImages());

            SendMessage sendMessage = new SendMessage()
                                    .setText( "<i>" + shopProposal.getText() + "</i>")
                                    .setParseMode(ParseMode.HTML)
                                    .setReplyMarkup(menuTrade(1));
            message.execute(sendMessage);
    }

    private void printHeader(Messages message){
        SendMessage sendMessage = new SendMessage()
                .setText("<b> ПРЕДЛОЖЕНИЕ </b>")
                .setParseMode(ParseMode.HTML)
                .disableNotification();
        message.execute(sendMessage);
    }

    private void printPhoto(Messages message, ArrayList<String> fileNames){
        for(String fileName : fileNames) {
            File file = new File(fileName);
            SendPhoto sendPhoto = new SendPhoto().setNewPhoto(file);
            message.sendPhoto(sendPhoto);
        }

        SendMessage sendMessage = new SendMessage()
                .setText("https://laboratory-holiday.ru/components/com_jshopping/files/img_products/full_g103.jpg")
                .setParseMode(ParseMode.HTML)
                .disableNotification();
        message.execute(sendMessage);

    }

    private InlineKeyboardMarkup menuTrade(Integer tradeId){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("Связаться с магазином")
                .setCallbackData(tradeId.toString()));
        rowInline.add(new InlineKeyboardButton("Cтраница магазина")
                .setUrl("https://laboratory-holiday.ru/"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("Посмотреть отзывы о магазине")
                .setCallbackData(tradeId.toString()));
        rowInline.add(new InlineKeyboardButton("Посмотреть еще предложения от магазина")
                .setCallbackData(tradeId.toString()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
