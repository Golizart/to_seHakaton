package bot.telegram.test.simple;

import java.time.Instant;

/**
 * Created by golizar on 19.11.17.
 */
public class Automat {
    private final BookingFlowers m_bookingFlowers;

    public Automat(BookingFlowers bookingFlowers){
        this.m_bookingFlowers = bookingFlowers;
    }

    public Automat(){
        this.m_bookingFlowers = new BookingFlowers();
    }

    public String Start(){
        switch (m_bookingFlowers.getCurrentState()){
            case 0: return TextByState.FLOWERS.getText();
            case 1: return TextByState.PRICE.getText();
            case 2: return TextByState.DELIVERY.getText();
            case 3: return TextByState.ADDRESS.getText();
            case 4: return TextByState.Date.getText();
            case 5: return TextByState.Date.getText();
            default: return End();
        }
    }

    public String nextState(String text){
        switch (m_bookingFlowers.getCurrentState()){
            case 0:  m_bookingFlowers.setFlowers(text); break;
            case 3:  m_bookingFlowers.setAddress(text); break;
        }
        return Start();
    }

    public String nextState(Double text){
        m_bookingFlowers.setPrice(text);
        return Start();
    }

    public String nextState(Boolean text){
        m_bookingFlowers.setDelivery(text);
        return Start();
    }

    public String nextState(Instant text){
        m_bookingFlowers.setDate(text);
        return End();
    }

    public String End(){
        return TextByState.End.getText();
    }

    public BookingFlowers getBookingFlowers() {
        return m_bookingFlowers;
    }
}
