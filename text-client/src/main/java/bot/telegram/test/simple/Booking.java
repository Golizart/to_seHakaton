package bot.telegram.test.simple;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by golizar on 23.11.17.
 */
public class Booking {
    private ArrayList<BookingInformation> mBookingInformationList = new ArrayList<BookingInformation>();

    public Booking() {
        for(int i = 0; i < 5; i++){
            BookingInformation booking;

            if((i % 2) == 0 ){
                Instant instantDF = Instant.parse("2017-11-0"+(i+1)+"T10:15:30.00Z");
                Instant instantDT = Instant.parse("2017-11-0"+(i+1)+"T15:15:30.00Z");
                booking = new BookingInformation("N"+1000+"S" +i, BookingStateEnum.DELIVERED,instantDF,instantDT);
            }
            else{
                Instant instantDF = Instant.parse("2017-11-1"+(i+1)+"T10:15:30.00Z");
                booking = new BookingInformation("N"+1000+"S" +i, BookingStateEnum.ORDERED,instantDF);
            }
            mBookingInformationList.add(booking);
        }
        Collections.sort(mBookingInformationList);
    }

    public ArrayList<BookingInformation> getBookingInformationList() {
        return mBookingInformationList;
    }
}
