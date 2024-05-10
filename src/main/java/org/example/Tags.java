package org.example;

public enum Tags {
    GENDER("성별"),
    RACE_COUNTRY("인종/국적"),
    YEAR("연령"),
    LOCALE("지역"),
    RELIGION("종교"),
    ABUSE("욕설"),
    ETC("기타"),
    CLEAN("clean");

    private final String tagName;
    Tags(String tagName) {
        this.tagName = tagName;
    }

    public String getTsvPosition(int row) {
        return (char) ('B' + ordinal()) + String.valueOf(row);
    }

    public static Tags convert(int kmhasTag) {
        return switch (kmhasTag) {
            case 0 -> LOCALE;
            case 1, 2 -> ETC;
            case 3 -> ABUSE;
            case 4 -> YEAR;
            case 5 -> GENDER;
            case 6 -> RACE_COUNTRY;
            case 7 -> RELIGION;
            case 8 -> CLEAN;
            default -> throw new IllegalArgumentException();
        };
    }

    public String getTagName() {
        return tagName;
    }
}
