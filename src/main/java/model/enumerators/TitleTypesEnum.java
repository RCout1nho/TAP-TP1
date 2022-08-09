package model.enumerators;

public enum TitleTypesEnum {
    BLURAY("BLURAY"),
    DVD("DVD"),
    CD("CD"),
    VIDEO_TAPE("VIDEO_TAPE");

    private String type;

    TitleTypesEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
