package bot.telegram.test.simple;

import java.time.Instant;


/**
 * Created by golizar on 19.11.17.
 */
public class BookingFlowers {
    private String m_flowers = null;
    private Double m_price = null;
    private Boolean m_delivery = null;
    private String m_address = null;
    private Instant m_date = null;
    private int currentState = 0;

    public BookingFlowers(){}

    public BookingFlowers(String flowers){
        this.m_flowers = flowers;
        this.currentState = 1;
    }

    public BookingFlowers(String flowers, Double price){
        this.m_flowers = flowers;
        this.m_price = price;
        this.currentState = 2;
    }

    public BookingFlowers(String flowers, Double price, Boolean delivery){
        this.m_flowers = flowers;
        this.m_price = price;
        this.m_delivery = delivery;
        this.currentState = 3;
    }

    public BookingFlowers(String flowers, Double price, Boolean delivery, String address){
        this.m_flowers = flowers;
        this.m_price = price;
        this.m_delivery = delivery;
        this.m_address = address;
        this.currentState = 4;
    }

    public BookingFlowers(String flowers, Double price, Boolean delivery, Instant date){
        this.m_flowers = flowers;
        this.m_price = price;
        this.m_delivery = delivery;
        this.m_date = date;
        this.currentState = 5;
    }


    public BookingFlowers(String flowers, Double price, Boolean delivery,String address, Instant date){
        this.m_flowers = flowers;
        this.m_price = price;
        this.m_delivery = delivery;
        this.m_date = date;
        this.m_address = address;
        this.currentState = 6;
    }

    public String getM_flowers() {
        return m_flowers;
    }

    public Double getM_price() {
        return m_price;
    }

    public Boolean getM_delivery() {
        return m_delivery;
    }

    public Instant getM_date() {
        return m_date;
    }

    public void setAddress(String m_address) {
        this.m_address = m_address;
        this.currentState = 5;
    }

    public String getM_address() {
        return m_address;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setFlowers(String m_flowers) {
        this.m_flowers = m_flowers;
        this.currentState = 1;
    }

    public void setDelivery(Boolean m_delivery) {
        this.m_delivery = m_delivery;
        this.currentState = 3;

    }

    public void setDate(Instant m_date) {
        this.m_date = m_date;
        if(m_delivery)
            this.currentState = 5;
        else
            this.currentState = 6;
    }

    public void setPrice(Double m_price) {
        this.m_price = m_price;
        this.currentState = 2;
    }
}
