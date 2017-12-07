package bot.telegram.test.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by golizar on 23.11.17.
 */
public class Shop {
    private final int mShopId;
    private final String mShopName;
    private final String mAddress;
    private final String mUrlShop;
    private String mUrlSite;
    private int mRating;
    private List<Integer> mStars = new ArrayList<>();
    private List<String> mReviws = new ArrayList<>();

    public Shop(int shopId, String shopName, String address, String urlShop) {
        this.mShopId = shopId;
        this.mShopName = shopName;
        this.mAddress = address;
        this.mUrlShop = urlShop;
    }

    public void setUrlSite(String urlSite) {
        this.mUrlSite = urlSite;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }

    public void setStars(List<Integer> stars) {
        this.mStars = mStars;
    }

    public void setReviws(List<String> reviws) {
        this.mReviws = mReviws;
    }

    public int getShopId() {
        return mShopId;
    }

    public String getShopName() {
        return mShopName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getUrlShop() {
        return mUrlShop;
    }

    public String getUrlSite() {
        return mUrlSite;
    }

    public int getRating() {
        return mRating;
    }

    public List<Integer> getStars() {
        return mStars;
    }

    public List<String> getReviws() {
        return mReviws;
    }
}
