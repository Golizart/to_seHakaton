package bot.botInterface;

/**
 * Created by golizar on 19.11.17.
 */
public enum MessageType {
    TELEGRAM("telegram");
    private final String m_message;

    MessageType(String message){
        this.m_message = message;
    }

    public String getText() {
        return m_message;
    }

}
