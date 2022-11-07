

public abstract class Staff {
    private String name;
    private String password;

    public Staff(String name,String password){

        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}