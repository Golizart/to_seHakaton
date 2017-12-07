package bot.telegram.test.simple;

import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import bot.real.proposals.RaealProposal;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleBot extends TelegramLongPollingBot {

    private AIDataService dataService;
    private Automat automat;
    public SimpleBot(AIDataService dataService) {
        this.dataService = dataService;
    }
    private static ViewEnum STATE_VIEW = ViewEnum.VIEW_VERTICAL;
    private static SortEnum STATE_SORT = SortEnum.SORT_BY_DEFAULT;
    private final static Booking BOOKING = new Booking();
    private final static Shop SHOP = new Shop(1, "OOO'Рога и Копыта'", "г.Краснодар. ул Красная 123", "myShop.ru");
    private RaealProposal raealProposal = null;
    private int currentStateAutomat = 1; // 0 - flowers; 1 - reviews
    private boolean automatStatus = false;
    private int currentStateAutomatReviews = 1; //1 - reviews ; 0 - stars
    private Integer mShopStars = 0;
    private String mShopReviews = "";
    private boolean mReviews = false;
    private boolean mStars = false;
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
        Message message = update.getMessage();
        CallbackQuery callbackQuery = update.getCallbackQuery();
        if(callbackQuery != null && !callbackQuery.getData().isEmpty()){
            message = callbackQuery.getMessage();
            if (message != null && message.hasText()) {
                findViewMenuSettings(message, callbackQuery);
                findSortMenuSettings(message, callbackQuery);
                findRating(message, callbackQuery);

                if(callbackQuery.getData().trim().equals("/menu")){
                    Menu(message, "/menu");
                }

                if (callbackQuery.getMessage().getText().trim().contains("Связаться с магазином")){
                    sendMsg(message, "call", null);
                }

                if(callbackQuery.getMessage().getText().trim().contains("Посмотреть отзывы о магазине")){
                    sendMsg(message, "reviews", null);
                }

                if(callbackQuery.getMessage().getText().trim().contains("Посмотреть еще предложения от магазина")){
                    sendMsg(message, "more", null);
                }


                String[] arrayQuery = callbackQuery.getData().split(",");
                for(String comand : arrayQuery){
                   if(!comand.isEmpty()){
                        String[] pair = comand.split(":");
                        if(pair.length > 1) {
                            System.out.println("Name = "+pair[0]+" value = "+ pair[1]);
                            if(pair[0].equals("command") && pair[1].equals("/reviews")){
                                currentStateAutomat = 1;
                                automatStatus = true;
                                currentStateAutomatReviews = 0;
                                addMenuForText(message, "Поставте оценку Магазину: \n", getMenuShopReviws());
                            }
                            if(pair[0].equals("stars")){

                            }
                        }
                    }
                }
            }
        }else
            if (message != null && message.hasText()) {
                if (raealProposal == null)
                    raealProposal = new RaealProposal(message.getChatId());


                if (automatStatus) {
                    if(currentStateAutomat == 1){
                        if(currentStateAutomatReviews == 1){
                            mShopReviews = message.getText();
                            mReviews = true;
                            if(mStars && mReviews){
                                automatStatus = false;
                                SHOP.getStars().add(mShopStars);
                                SHOP.getReviws().add(mShopReviews);
                                reviwsAddSuccess(message);
                            }else{
                                addMenuForText(message, "Поставте оценку Магазину: \n", getMenuShopReviws());
                                currentStateAutomatReviews = 0;
                            }
                        }else{
                            addMenuForText(message, "Поставте оценку Магазину: \n", getMenuShopReviws());
                            currentStateAutomatReviews = 0;
                        }
                    }else{

                    }
                }else if(message.getText().toLowerCase().equals("/menu")||message.getText().toLowerCase().equals("перейти в меню")){
                    Menu(message, "/menu");
                }else if(message.getText().toLowerCase().equals("заказать цветы")){

                }else if(message.getText().toLowerCase().equals("мои заказы") ||  message.getText().equals("/myBooking")){
                    sendMsg(message, "Для перехода в мои заказы воспользуйтесь командой /myBooking", null);
                    for (BookingInforamtion bookingInforamtion :BOOKING.getBookingInforamtionList()){
                        addMenuForText(message, bookingInforamtion.toString(), bookingInforamtion.getMenu());
                    }
                }else if(message.getText().toLowerCase().equals("закрыть меню")){
                    sendMsg(message, "Для вызова меню воспользуйтесь командой /menu", null);
                }else if(message.getText().toLowerCase().equals("мои настройки") || message.getText().equals("/settings")){
                    mySettings(message,  "/settings");
                }else if(message.getText().toLowerCase().equals("выбрать сортировку запросов") || message.getText().equals("/selectSort")) {
                    mySettingsSort(message, "текущая сортировка : " + STATE_SORT.getText());
                }else if(message.getText().toLowerCase().equals("вид отбражения результатов") || message.getText().equals("/selectView")){
                    mySettingsView(message, "текущий вид : " + STATE_VIEW.getText());
                }else if(message.getText().toLowerCase().equals("отобразить все мои настройки") || message.getText().equals("/printSettings")){
                    mySettingsView(message, printMySettings());
                }if(message.getText().toLowerCase().equals("/start")){
                    startButton(message, "Добрый день, этот бот вам поможет заказать лучшие цветы в Краснодаре! \n" +
                            "Введите запрос или воспользуйтесь кнопкой меню");
                }else
                    requestHandling(message);
            }
    }

    private void findViewMenuSettings(Message message, CallbackQuery callbackQuery){

        if(callbackQuery.getData().equals(ViewEnum.VIEW_HORIZONTAL.getCommand())){
            sendMsg(message, "Установлено " + ViewEnum.VIEW_HORIZONTAL.getText() + " отображение", null);
            STATE_VIEW = ViewEnum.VIEW_HORIZONTAL;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");
        }

        if(callbackQuery.getData().equals(ViewEnum.VIEW_VERTICAL.getCommand())){
            sendMsg(message, "Установлено " + ViewEnum.VIEW_VERTICAL.getText() + " отображение", null);
            STATE_VIEW = ViewEnum.VIEW_VERTICAL;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");
        }

    }

    private void findRating(Message message, CallbackQuery callbackQuery) {

        if (automatStatus && (currentStateAutomat == 1) && (currentStateAutomatReviews == 0)) {
            String stars = "\uD83C\uDF1F";
            String[] arrayRatingShop = callbackQuery.getData().split(",");
            if (arrayRatingShop.length == 2) {
                String[] Shop = arrayRatingShop[0].split(":");
                String[] Stars = arrayRatingShop[1].split(":");
                if(Shop[0].equals("shopId") && Stars[0].equals("stars")){
                    mShopStars = Integer.parseInt(Stars[1]);
                    mStars = true;
                }

            }

            if(mStars && mReviews){
                automatStatus = false;
                SHOP.getStars().add(mShopStars);
                SHOP.getReviws().add(mShopReviews);
                reviwsAddSuccess(message);
            }else{
                sendMsg(message, "Напишите отзыв магазину", null);
                currentStateAutomatReviews = 1;
            }
        }
    }

    private void reviwsAddSuccess(Message message){
        int index = SHOP.getStars().size() - 1;
        String rewiw = "Ваш отзыв успешно добавлен! \n" +
                "Оценка:" + SHOP.getStars().get(index) + "\n"+
                "Отзыв: " + SHOP.getReviws().get(index);
        sendMsg(message, rewiw, null);
        startButton(message, "Для заказа цывтов введите запрос или воспользуйтесь кнопкой меню");

    }

    private String printMySettings()
    {
        String resultValue = "сортировка: " + STATE_SORT.getText() +"\n" +
                             "отображение: "  + STATE_VIEW.getText() +"\n";
        return resultValue;
    }

    private void findSortMenuSettings(Message message, CallbackQuery callbackQuery){

        if(callbackQuery.getData().equals(SortEnum.SORT_BY_DEFAULT.getCommand())){
            sendMsg(message, "Установлена сортировка по времни ответа " , null);
            STATE_SORT = SortEnum.SORT_BY_DEFAULT;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");
        }

        if(callbackQuery.getData().equals(SortEnum.SORT_BY_DISTANCE.getCommand())){
            sendMsg(message, "Установлена сортровка по удаленности ", null);
            STATE_SORT = SortEnum.SORT_BY_DISTANCE;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");
        }

        if(callbackQuery.getData().equals(SortEnum.SORT_BY_RATING.getCommand())){
            sendMsg(message, "Установлена сортровка по рейтингу ", null);
            STATE_SORT = SortEnum.SORT_BY_RATING;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");

        }

        if(callbackQuery.getData().equals(SortEnum.SORT_BY_POPULARITY.getCommand())){
            sendMsg(message, "Установлена сортровка по популярности магазина ", null);
            STATE_SORT = SortEnum.SORT_BY_POPULARITY;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");
        }

        if(callbackQuery.getData().equals(SortEnum.SORT_BY_PRICE.getCommand())){
            sendMsg(message, "Установлена сортровка по цене ", null);
            STATE_SORT = SortEnum.SORT_BY_PRICE;
            startButton(message, "Введите запрос или воспользуйтесь кнопкой меню");
        }
    }


    private void requestHandling(Message message){
        try {
            AIRequest request = new AIRequest(message.getText());
            AIResponse response = dataService.request(request);
            if (response.getStatus().getCode() == 200) {
                System.out.println(response.getResult());
                HashMap<String, JsonElement> param =  response.getResult().getParameters();
                HashMap<Long, JsonElement>  proposals = null;
                if (!param.isEmpty()){
                    proposals = raealProposal.FindDiler(param, message.getText());
                    int i = 1;
                    for (Map.Entry<Long, JsonElement> entry : proposals.entrySet())
                    {
                        Gson gson = new Gson();
                        HashMap<String, Object> arr= gson.fromJson(entry.getValue(), HashMap.class);
                        ArrayList<String> images = (ArrayList<String>) arr.get("images");
                        if(images.isEmpty())
                            sendMsg(message, (String) arr.get("message"), null);
                        else
                            sendMsg(message, (String) arr.get("message"), images, i);
                        i++;
                    }
                }else{
                    sendMsg(message, response.getResult().getFulfillment().getSpeech(), null);
                }

            } else {
                sendMsg(message, response.getStatus().getErrorDetails(), 1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void CreateBookingFlowers(HashMap<String, JsonElement> param ){
        Instant instant = Instant.parse(param.get("date")+"T"+param.get("time1")+"Z");
        BookingFlowers bookingFlowers = null;
        if(param.get("Bay") != null){
            if(param.get("number") != null){
                if(param.get("number") != null){

                }else{

                }
            }else{
                bookingFlowers = new BookingFlowers(param.get("Bay").toString());
            }
        }else{
            bookingFlowers = new BookingFlowers();
        }
    }

    private void sendMsg(Message message, String text, Integer replayMessageId) {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), text)
                .setParseMode(ParseMode.HTML);
        sendMessage.setReplyMarkup(removeKeyBoard());
        if(replayMessageId != null)
            sendMessage.setReplyToMessageId(replayMessageId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void addMenuForText(Message message, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), text)
                .setParseMode(ParseMode.HTML)
                .setReplyMarkup(replyKeyboard);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void sendMsg(Message message, String text, ArrayList<String> fileNames, int i) {
        try {
            printHeader(message.getChatId().toString(), i);
            //printPhoto(message.getChatId().toString(), fileNames);
            sendMsg(message, "https://laboratory-holiday.ru/components/com_jshopping/files/img_products/full_g103.jpg", 0);
            addMenuForText(message, "<i>" + text + "</i>", menuTrade(1));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void printHeader(String chatId, int i) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage(chatId,"<b> ПРЕДЛОЖЕНИЕ № "+i+" </b>")
                .setParseMode(ParseMode.HTML)
                .disableNotification()
                .setReplyMarkup(removeKeyBoard());
        execute(sendMessage);
    }


    private void printPhoto(String chatId, ArrayList<String> fileNames) throws TelegramApiException {
        for(String fileName : fileNames) {

            File file = new File(fileName);
            Pattern p = Pattern.compile("[A-Za-z0-9_]+\\.jpg");
            Matcher m = p.matcher(fileName);
            if(m.find())
                System.out.println(m.group());

            SendPhoto sendPhoto = new SendPhoto()
                    .setChatId(chatId)
                    .setNewPhoto(file)
                    .setReplyMarkup(removeKeyBoard());

            sendPhoto(sendPhoto);
        }

    }

    private void startButton(Message message, String text){

        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("Меню")
                .setCallbackData("/menu"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markupInline);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardRemove removeKeyBoard(){
        ReplyKeyboardRemove replyKeyboardMarkup = new ReplyKeyboardRemove();
        return replyKeyboardMarkup;
    }

    private void Menu(Message message, String text){

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Заказать цветы");
        keyboardFirstRow.add("Мои заказы");
        keyboard.add(keyboardFirstRow);

        keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Закрыть меню");
        keyboardFirstRow.add("Мои настройки");
        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void mySettings(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Выбрать сортировку запросов");
        keyboardFirstRow.add("Вид отбражения результатов");
        keyboard.add(keyboardFirstRow);
        keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Перейти в меню");
        keyboardFirstRow.add("Отобразить все мои настройки");
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void mySettingsView(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(ViewEnum.VIEW_HORIZONTAL.getText())
                .setCallbackData(ViewEnum.VIEW_HORIZONTAL.getCommand()));
        rowInline.add(new InlineKeyboardButton(ViewEnum.VIEW_VERTICAL.getText())
                .setCallbackData(ViewEnum.VIEW_VERTICAL.getCommand()));
        rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markupInline);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void mySettingsSort(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_DISTANCE.getText())
                .setCallbackData(SortEnum.SORT_BY_DISTANCE.getCommand()));
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_POPULARITY.getText())
                .setCallbackData(SortEnum.SORT_BY_POPULARITY.getCommand()));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_RATING.getText())
                .setCallbackData(SortEnum.SORT_BY_RATING.getCommand()));
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_DEFAULT.getText())
                .setCallbackData(SortEnum.SORT_BY_DEFAULT.getCommand()));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton(SortEnum.SORT_BY_PRICE.getText())
                .setCallbackData(SortEnum.SORT_BY_PRICE.getCommand()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(markupInline);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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

    public InlineKeyboardMarkup getMenuShopReviws(){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("\uD83C\uDF1F")
                .setCallbackData("shopId:1,stars:1"));
        rowInline.add(new InlineKeyboardButton("\uD83C\uDF1F"+"\uD83C\uDF1F")
                .setCallbackData("shopId:1,stars:2"));
        rowInline.add(new InlineKeyboardButton("\uD83C\uDF1F"+"\uD83C\uDF1F"+"\uD83C\uDF1F")
                .setCallbackData("shopId:1,stars:3"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton("\uD83C\uDF1F"+"\uD83C\uDF1F"+"\uD83C\uDF1F"+"\uD83C\uDF1F")
                .setCallbackData("shopId:1,stars:4"));
        rowInline.add(new InlineKeyboardButton("\uD83C\uDF1F"+"\uD83C\uDF1F"+"\uD83C\uDF1F"+"\uD83C\uDF1F"+"\uD83C\uDF1F")
                .setCallbackData("shopId:1,stars:5"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }

}