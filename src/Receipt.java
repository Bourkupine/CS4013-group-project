/**
 * Receipt is the details of orders that are written to csv files
 */
public class Receipt implements ReadWrite{

    private Restaurant r;

    private Order o;

    public Receipt(Restaurant r, Order o) {
        this.r = r;
        this.o = o;
        updateFile(r.getMoney(),toCsv());
    }

    public String toCsv(){
        return o.getDate()+",€"+o.getTotal();
    }
}
