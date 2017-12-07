package bot.telegram.test.simple;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by golizar on 23.11.17.
 */
public class Booking {
    private ArrayList<BookingInforamtion> mBookingInforamtionList = new ArrayList<BookingInforamtion>();

    public Booking() {
        for(int i = 0; i < 5; i++){
            BookingInforamtion booking;

            if((i % 2) == 0 ){
                Instant instantDF = Instant.parse("2017-11-0"+(i+1)+"T10:15:30.00Z");
                Instant instantDT = Instant.parse("2017-11-0"+(i+1)+"T15:15:30.00Z");
                booking = new BookingInforamtion("N"+1000+"S" +i, BookingStateEnum.DELIVERED,instantDF,instantDT);
            }
            else{
                Instant instantDF = Instant.parse("2017-11-1"+(i+1)+"T10:15:30.00Z");
                booking = new BookingInforamtion("N"+1000+"S" +i, BookingStateEnum.ORDERED,instantDF);
            }
            mBookingInforamtionList.add(booking);
        }
        Collections.sort(mBookingInforamtionList);
    }

    public ArrayList<BookingInforamtion> getBookingInforamtionList() {
        return mBookingInforamtionList;
    }
}
