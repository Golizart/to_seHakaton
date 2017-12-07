package bot.telegram.test.simple;

/**
 * Created by golizar on 19.11.17.
 */
public enum BookingStateEnum {
    ORDERED("Заказано", "/ordered"),
    DELIVERED("Доставлено", "/delivered");

    private final String m_state;
    private final String m_command;
    BookingStateEnum(String state, String command){
        this.m_state = state;
        this.m_command = command;
    }

    public String getText() {
        return m_state;
    }

    public String getCommand() {
        return m_command;

    }
}
