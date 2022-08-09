package model.enumerators;

public class ComboBoxItem {
    private Integer id;
    private String label;

    public ComboBoxItem(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
