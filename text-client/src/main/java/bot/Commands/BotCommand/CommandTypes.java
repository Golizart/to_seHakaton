package bot.Commands.BotCommand;

/**
 * Created by golizar on 08.12.17.
 */
public enum CommandTypes {
    BOOKING("booking", new String[]{"/booking", "\uD83D\uDCB3 Сделать заказ"}),
    CLOSE_MENU("close menu", new String[]{"/closeMenu", "\u274C Закрыть меню"}),
    MENU("menu", new String[]{"/menu","перейти в меню", "меню"}),
    MY_BOOKINGS("my bookings",  new String[]{"/myBooking", "\uD83D\uDED2 Мои заказы"}),
    SETTINGS("settings", new String[]{"/settings", "\uD83C\uDF9B️️ Мои настройки"}),
    PRINT_SETTINGS("print settings", new String[]{"/printSettings", "\uD83D\uDCDC Отобразить все мои настройки"}),
    SORT_SETTINGS("sort settings", new String[]{"/sortSettings", "\uD83D\uDCCA Выбрать сортировку запросов"}),
    VIEW_SETTINGS("view Settings", new String[]{ "/viewSettings", "\uD83D\uDCF1 Вид отбражения результатов"}),
    START("start", new String[]{"/start"}),
    CALL("call shop", new String[]{"/call"}),
    REVIEWS("reviews",  new String[]{"/reviews"}),
    MORE_PROPOSALS("more proposals", new String[]{"/moreProposals"}),
    VIEW_SHOP_RESULT("view shop result", null ),
    SPEECH("AI tolk", null );

    private final String commandName;
    private String[] commandVariant;
    CommandTypes(String commandName, String[] commandVariant ) {
        this.commandName = commandName;
        this.commandVariant = commandVariant;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommand() {
        return commandVariant[0];
    }

    public String[] getCommandVariant() {
        return commandVariant;
    }
}
