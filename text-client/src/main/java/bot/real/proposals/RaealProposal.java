package bot.real.proposals;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.telegram.telegrambots.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akurus on 23.09.17.
 */
public class RaealProposal {

    private long chatId;
    private HashMap<Long, JsonElement> diler = new HashMap<Long, JsonElement> ();
    JsonElement dilerSay;

    public RaealProposal(long chatId) {
        this.chatId = chatId;
        JsonParser jsonParser = new JsonParser();
        String jsonStringdiler1 = "{\n" +
                "  \" chatId\": \"123456\",\n" +
                "  \"message\": \"Можем доставить букет за 750Р через 10 мин\",\n" +
                "  \"images\": [\n" +
                "      \"pick.jpg\"\n" +
                "    ]}";

        String jsonStringdiler2 = "{\n" +
                "  \" chatId\": \"123457\",\n" +
                "  \"message\": \"Наш магазин находиться 2 минутах ходьбы от места доставки. За самовывоз сделаем скидку 10%. адрес: Красная 125\",\n" +
                "  \"images\": [\n" +
                "      \"pick.jpg\",\n" +
                "      \"pick2.jpg\"\n" +
                "    ]}";

        String jsonStringdiler3 = "{\n" +
                "  \" hatId\": \"123458\",\n" +
                "  \"message\": \"Можем доставить  через 5 мин, цена :800р\",\n" +
                "  \"images\": [\n" +
                "      \"pick.jpg\",\n" +
                "      \"pick2.jpg\"\n" +
                "    ]}";

        JsonElement jsonElement1 = jsonParser.parse(jsonStringdiler1);
        JsonElement jsonElement2 = jsonParser.parse(jsonStringdiler2);
        JsonElement jsonElement3 = jsonParser.parse(jsonStringdiler3);
        diler.put(1L,jsonElement1);
        diler.put(2L,jsonElement2);
        diler.put(3L,jsonElement3);
    }


    public HashMap<Long, JsonElement> FindDiler( HashMap<String, JsonElement> param)
    {
        for (Map.Entry<String, JsonElement> entry : param.entrySet())
        {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            System.out.println("key = "+ key +"; value = "+ value);
        }
        return diler;
    }

    public JsonElement connectDiler(Message message, long dillerId){

        return dilerSay;
    }

}
