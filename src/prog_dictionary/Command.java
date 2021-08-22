package prog_dictionary;

public enum Command {
    PRINT_DICTIONARY("#","распечатать весь словарь"),
    EXIT("@","выход")
    ;

    private String key;
    private String nameRus;

    Command(String key, String nameRus) {
        this.key = key;
        this.nameRus = nameRus;
    }

    public String getKey() {
        return key;
    }

    public String getNameRus() {
        return nameRus;
    }
}
