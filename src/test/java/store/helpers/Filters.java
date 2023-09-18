package store.helpers;

public enum Filters {
    HIGHLIGHTS("Highlights"),
    MARKE("Marke"),
    PRODUKTART("Produktart"),
    GESCHENK("Geschenk"),
    FURWHEN("Für Wen");

    public final String label;

    Filters(String label) {
        this.label = label;
    }

    public String getFilter() {
        return label;
    }
}
