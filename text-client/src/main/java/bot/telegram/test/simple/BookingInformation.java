package bot.telegram.test.simple;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by golizar on 23.11.17.
 */
public class BookingInformation implements Comparator,Comparable {
    private final String mBookingNumber;
    private final String mShopName;
    private final Integer mShopId;
    private final Instant mDateFrom;
    private Instant mDateTo = null;
    private BookingStateEnum mBookingState;

    public BookingInformation(String mBookingNumber, BookingStateEnum mBookingState, Instant dateFrom) {
        this.mBookingNumber = mBookingNumber;
        this.mBookingState = mBookingState;
        this.mShopId = 1;
        this.mShopName = "OOO 'Рога и копыта'";
        this.mDateFrom = dateFrom;
    }

    public BookingInformation(String mBookingNumber, BookingStateEnum mBookingState, Instant dateFrom, Instant dateTo) {
        this.mBookingNumber = mBookingNumber;
        this.mBookingState = mBookingState;
        this.mShopId = 1;
        this.mShopName = "OOO 'Рога и копыта'";
        this.mDateFrom = dateFrom;
        this.mDateTo = dateTo;
    }

    public String getBookingNumber() {
        return mBookingNumber;
    }

    public BookingStateEnum getBookingState() {
        return mBookingState;
    }

    public int getTradeId() {
        return mShopId;
    }

    public Instant getDateFrom() {
        return mDateFrom;
    }

    public void setDateTo(Instant dateTo) {
        this.mDateTo = dateTo;
    }

    @Override
    public String toString(){
        String resultValue = "Номер заказа: " + mBookingNumber +"\n" +
                             "Название магазина: "  + mShopName +"\n" +
                             "Статус доставки: "  + mBookingState.getText() +"\n" +
                             "Дата заказа: "+ mDateFrom.toString();
        if(this.mDateTo != null)
            resultValue += "\n" +"Доставленно:" + mDateTo.toString();

        return resultValue;

    }

    public InlineKeyboardMarkup getMenu(){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        if(mBookingState == BookingStateEnum.DELIVERED){
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton("\uD83D\uDCDD"+" Оставить отзыв")
                    .setCallbackData("command:/reviews,shopId:"+mShopId.toString()));
            rowInline.add(new InlineKeyboardButton("\uD83D\uDCE1"+" Связаться с магазином")
                    .setCallbackData("command:/callShop,shopId:" + mShopId.toString()));
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
        }
        return markupInline;
    }

    public int compare(Object obj1, Object obj2) {
        Instant p1 = ((BookingInformation) obj1).getDateFrom();
        Instant p2 = ((BookingInformation) obj2).getDateFrom();
        return p1.compareTo(p2);
    }

    @Override
    public int compareTo(Object obj1) {
        Instant p1 = ((BookingInformation) obj1).getDateFrom();
        return this.getDateFrom().compareTo(p1);
    }
}
