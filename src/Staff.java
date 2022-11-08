

public abstract class Staff {
    private String name;
    private String password;
    private Restaurant rest;

    public Staff(String name,String password, Restaurant rest){
        this.rest=rest;
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Restaurant getRest() {
        return rest;
    }
}