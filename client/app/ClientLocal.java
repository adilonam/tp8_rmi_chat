package app;


public class ClientLocal {
    private String id;
    private String name;

    public ClientLocal(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}