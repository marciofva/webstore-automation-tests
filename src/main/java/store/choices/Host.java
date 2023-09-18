package store.choices;


public enum Host {
    LOCALHOST("host.localhost"),
    DOCKER_SELENIUM_GRID("host.docker.selenium.grid");

    public final String label;

    Host(String label) {
        this.label = label;
    }

    public String getHost() {
        return label;
    }
}
