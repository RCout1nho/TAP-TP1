package tp01.model.enumerators;

public enum UserTypeEnum {
    CLIENT("Cliente"),
    ADMIN("Admin");

    private final String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
