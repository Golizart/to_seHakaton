package bot.telegram.test.simple;

import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import com.google.gson.JsonElement;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import bot.real.proposals.RaealProposal;
import java.util.HashMap;
import java.util.Map;

public class SimpleBot extends TelegramLongPollingBot {

    private AIDataService dataService;
    public SimpleBot(AIDataService dataService) {
        this.dataService = dataService;
    }

    private RaealProposal raealProposal = null;

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (raealProposal == null)
            {
                raealProposal = new RaealProposal(message.getChatId());
            }
            try {
            AIRequest request = new AIRequest(message.getText());
            AIResponse response = dataService.request(request);

            if (response.getStatus().getCode() == 200) {
                HashMap<String, JsonElement> param =  response.getResult().getParameters();
                if (!param.isEmpty()){

                }
                for (Map.Entry<String, JsonElement> entry : param.entrySet())
                {
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();
                    System.out.println("key = "+ key +"; value = "+ value);
                }
                sendMsg(message, response.getResult().getFulfillment().getSpeech());
            } else {
                sendMsg(message,response.getStatus().getErrorDetails());
            }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}