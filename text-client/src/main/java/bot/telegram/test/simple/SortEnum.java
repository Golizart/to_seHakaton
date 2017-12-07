package bot.telegram.test.simple;

/**
 * Created by golizar on 19.11.17.
 */
public enum SortEnum {
    SORT_BY_RATING("\uD83C\uDF1F"+" по рейтингу", "/rating"),
    SORT_BY_DEFAULT("\uD83D\uDD50"+" по времени ответа", "/sortDefault"),
    SORT_BY_DISTANCE("\uD83C\uDFC3"+" по удаленности", "/sortDistance"),
    SORT_BY_PRICE("\uD83D\uDCB2" + " по цене", "/sortPrice"),
    SORT_BY_POPULARITY("\uD83D\uDCC8"+"по популярности", "/SortPopularity");

    private final String m_state;
    private final String m_command;
    SortEnum(String state,String command){
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
