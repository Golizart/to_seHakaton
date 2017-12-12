package bot.Commands.BotCommand;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import bot.Commands.Shop.ViewShopResultCommand;
import bot.Entity.Entity;
import bot.real.proposals.RealProposal;
import com.google.gson.JsonElement;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import java.util.HashMap;


public class SpeechCommand implements Command{
    private  static SpeechCommand speechCommand;
    private final AIDataService dataService;
    public static SpeechCommand getInstance(){
        if(speechCommand == null)
            speechCommand = new SpeechCommand();
        return speechCommand;
    }

    public SpeechCommand() {
        ApiContextInitializer.init();
        AIConfiguration configuration = new AIConfiguration("81c46bba0ec34d80a17c2b265ea7ccc8");
        this.dataService = new AIDataService(configuration);
    }

    @Override
    public void execute(Entity entity) {
        try{
            RealProposal realProposal = new RealProposal(entity.getMessages().getChatId());
            AIRequest request = new AIRequest(entity.getMessages().getText());
            AIResponse response = dataService.request(request);
            if (response.getStatus().getCode() == 200) {
                System.out.println(response.getResult());
                HashMap<String, JsonElement> param =  response.getResult().getParameters();
                if (!param.isEmpty()){
                    HashMap<Long, JsonElement> proposals = realProposal.FindDiler(param, entity.getMessages().getText());
                    ViewShopResultCommand.getInstance().execute(entity, proposals);
                }else{
                    SendMessage sendMessage = new SendMessage()
                            .setText( response.getResult().getFulfillment().getSpeech())
                            .setParseMode(ParseMode.HTML);
                    entity.getMessages().execute(sendMessage);
                 }

            } else {
                SendMessage sendMessage = new SendMessage()
                        .setText(response.getStatus().getErrorDetails())
                        .setParseMode(ParseMode.HTML);
                entity.getMessages().execute(sendMessage);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.CALL;
    }
}
