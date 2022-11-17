import java.util.Objects;

public abstract class Staff {
    private String name;
    private String password;
    private Restaurant rest;

    /**
     * Staff constructor
     * @param name name of staff member
     * @param password password of staff member
     * @param rest restaurant that staff member works at
     * @author Ronan, Thomas
     */
    public Staff(String name,String password, Restaurant rest){
        this.rest=rest;
        this.name=name;
        this.password=password;
    }

    /**
     * gets the staff members name
     * @return name as string
     * @author Ronan
     */
    public String getName() {
        return name;
    }

    /**
     * gets staff members password
     * @return password as string
     * @author Ronan
     */
    //maybe change permissions here
    public String getPassword() {
        return password;
    }

    /**
     * gets the restaurant that staff members works at
     * @return restaurant as a restaurant object
     * @author Thomas
     */
    public Restaurant getRest() {
        return this.rest;
    }

    /**
     * check if two staff members are the same
     * @param o object to be passed
     * @return true/false if they are the same
     * @author Bayan
     */
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

    public void printOrders() {
        for (Order order : rest.getOrders()) {
            System.out.println(order);
        }
    }
}