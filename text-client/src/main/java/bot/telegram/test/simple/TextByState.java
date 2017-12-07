package bot.telegram.test.simple;

/**
 * Created by golizar on 19.11.17.
 */
public enum TextByState {
    FLOWERS("Напишите, какой букет вам нужен"),
    PRICE("Укажите максимальную цену"),
    DELIVERY("Нужна ли вам доставка?"),
    ADDRESS("Укажите адрес доставки"),
    Date("Укажите дату доставки"),
    End("Заказ сформирован");

    private final String m_text;
    TextByState(String text){
        this.m_text = text;
    }

    public java.lang.String getText() {
        return m_text;
    }
}
