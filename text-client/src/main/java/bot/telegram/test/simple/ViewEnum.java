package bot.telegram.test.simple;

/**
 * Created by golizar on 19.11.17.
 */
public enum ViewEnum {
    VIEW_VERTICAL("\u2195" + "вертикальное", "/viewVerticval"),
    VIEW_HORIZONTAL("\u2194" + "горизонтальное","/viewHorizontal");

    private final String m_state;
    private final String m_command;
    ViewEnum(String state, String command){
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
