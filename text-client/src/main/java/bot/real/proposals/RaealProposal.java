package bot.real.proposals;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.telegram.telegrambots.api.objects.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by akurus on 23.09.17.
 */
public class RaealProposal {
    private long chatId;
    private HashMap<Long, JsonElement> diler = new HashMap<Long, JsonElement> ();
    JsonElement dilerSay;
    String bootstrapServers = "kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093" +
            ",kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093" +
            ",kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093" +
            ",kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093," +
            "kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093";
    String adminRestURL = "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443";
    String apiKey = "pX8kdXiiz6vqvPZd4YL0B5vyxhx09NxXA1CSlwgoY10eeZ8R";
    String user = apiKey.substring(0, 16);
    String password = apiKey.substring(16);
    private static String resourceDir;
    final Properties clientProperties = new Properties();

    public RaealProposal(long chatId) {
        this.chatId = chatId;
        JsonParser jsonParser = new JsonParser();
        resourceDir = System.getProperty("user.dir") + File.separator + "resources";
        String jsonStringdiler1 = "{\n" +
                "  \" chatId\": \"123456\",\n" +
                "  \"message\": \"Цена: 750Р \nВремя доставки: 10 мин\",\n" +
                "  \"images\": [\n" +
                "      \"/media/golizar/Project/HACKATON/MTS_2Д/flowers/full_r911.jpg\"\n" +
                "    ]}";

        String jsonStringdiler2 = "{\n" +
                "  \" chatId\": \"123457\",\n" +
                "  \"message\": \" Цена: 720р \nСкидка: 10% \nАдрес Магазина: Красная 125 \nДоставка: Самовывоз \nКоментарий: Наш магазин находиться 2 минутах ходьбы от места доставки. \",\n" +
                "  \"images\": [\n" +
                "      \"/media/golizar/Project/HACKATON/MTS_2Д/flowers/full_R902.jpg\",\n" +
                "      \"/media/golizar/Project/HACKATON/MTS_2Д/flowers/full_b731.jpg\"\n" +
                "    ]}";

        String jsonStringdiler3 = "{\n" +
                "  \" hatId\": \"123458\",\n" +
                "  \"message\": \"Цена :800р\nВремя доставки: 5 мин\",\n" +
                "  \"images\": [\n" +
                "      \"/media/golizar/Project/HACKATON/MTS_2Д/flowers/full_b730_1_.jpg\",\n" +
                "      \"/media/golizar/Project/HACKATON/MTS_2Д/flowers/full_b727.jpg\"\n" +
                "    ]}";

        JsonElement jsonElement1 = jsonParser.parse(jsonStringdiler1);
        JsonElement jsonElement2 = jsonParser.parse(jsonStringdiler2);
        JsonElement jsonElement3 = jsonParser.parse(jsonStringdiler3);
        diler.put(1L,jsonElement1);
        diler.put(2L,jsonElement2);
        diler.put(3L,jsonElement3);
    }


    public HashMap<Long, JsonElement> FindDiler( HashMap<String, JsonElement> param, String text)
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
