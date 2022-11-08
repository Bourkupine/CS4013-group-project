



public class Manager extends Staff {

    public Manager(String name,String password, Restaurant rest){
        super(name,password,rest);
    }


    public void employStaff(Staff staff) {
        getRest().getStaff().add(staff);
    }

    public void fireStaff(Staff staff) {
        getRest().getStaff().remove(staff);
    }





}
