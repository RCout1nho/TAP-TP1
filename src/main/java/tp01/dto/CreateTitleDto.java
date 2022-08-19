package tp01.dto;

public class CreateTitleDto {
    private String name;
    private String type;
    private Integer quantity;
    private Integer maxPeriodOfRent;

    public CreateTitleDto(String name, String type, Integer quantity, Integer maxPeriodOfRent) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.maxPeriodOfRent = maxPeriodOfRent;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getMaxPeriodOfRent() {
        return maxPeriodOfRent;
    }
}
