import java.util.Objects;

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
        return this.rest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Staff staff = (Staff) o;
        return Objects.equals(name, staff.name) && Objects.equals(password, staff.password) && Objects.equals(rest, staff.rest);
    }
}