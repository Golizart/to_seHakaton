package bot.Buisnes;

import java.util.ArrayList;

/**
 * Created by golizar on 10.12.17.
 */
public class ShopProposal {
    private final String text;
    private final Shop shop;
    private final ArrayList<String> images;

    public ShopProposal(String text, Shop shop, ArrayList<String> images) {
        this.text = text;
        this.shop = shop;
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public Shop getShop() {
        return shop;
    }

    public ArrayList<String> getImages() {
        return images;
    }
}
