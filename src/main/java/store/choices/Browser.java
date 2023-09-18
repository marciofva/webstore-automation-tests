package store.choices;

public enum Browser {
    CHROME("chrome"),
    EDGE("edge"),
    FIREFOX("firefox");

    public final String label;

    Browser(String label) {
        this.label = label;
    }

    public String getBrowser() {
        return label;
    }
}
