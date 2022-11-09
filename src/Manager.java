public class Manager extends Staff {

    public Manager(String name,String password, Restaurant rest){
        super(name,password,rest);
    }

    public void addToMenu(FoodItem food) {
        getRest().getMenu().addFood(food);
    }

    public void removeFromMenu(FoodItem food) {
        getRest().getMenu().removeFood(food);
    }

    public void employStaff(Staff staff) {
        getRest().getStaff().add(staff);
    }

    public void fireStaff(Staff staff) {
        getRest().getStaff().remove(staff);
    }
}
