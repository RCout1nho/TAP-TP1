package tp01.model;


public class Title {
    public Integer id;
    public String name;
    public String type;
    public Integer quantity;
    public Integer maxPeriodOfRent;

    public Title(Integer id, String name, String type, Integer quantity, Integer maxPeriodOfRent) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.maxPeriodOfRent = maxPeriodOfRent;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", quantity=" + quantity +
                ", maxPeriodOfRent=" + maxPeriodOfRent +
                '}';
    }
}
