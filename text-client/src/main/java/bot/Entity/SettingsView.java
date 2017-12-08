package bot.Entity;

import bot.telegram.test.simple.SortEnum;
import bot.telegram.test.simple.ViewEnum;

/**
 * Created by golizar on 08.12.17.
 */
public class SettingsView {

    private static ViewEnum STATE_VIEW = ViewEnum.VIEW_VERTICAL;
    private static SortEnum STATE_SORT = SortEnum.SORT_BY_DEFAULT;

    public static ViewEnum getStateView() {
        return STATE_VIEW;
    }

    public static SortEnum getStateSort() {
        return STATE_SORT;
    }
}
