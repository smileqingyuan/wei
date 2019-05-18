package win.smile.enumdemo;

public enum SizeEnum {

    SMALL("S", "小"),
    MEDIUM("M", "中"),
    LARGE("L", "大"),
    EXTRA_LARGE("XL", "超大");

    private String abbreviation;
    private String enSize;

    private SizeEnum(String abbreviation, String enSize) {
        this.abbreviation = abbreviation;
        this.enSize = enSize;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getEnSize() {
        return enSize;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}


