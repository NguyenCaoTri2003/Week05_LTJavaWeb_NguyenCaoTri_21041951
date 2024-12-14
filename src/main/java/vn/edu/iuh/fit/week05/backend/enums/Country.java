package vn.edu.iuh.fit.week05.backend.enums;

public enum Country {
    VIETNAM(1), USA(2), JAPAN(3), KOREA(4), CHINA(5);
    private int value;

    private Country(int value) {
        this.value = value;
    }

    public static Country getCountry(int value) {
        if (value == 1) {
            return Country.VIETNAM;
        } else if (value == 2) {
            return Country.USA;
        } else if (value == 3) {
            return Country.JAPAN;
        } else if (value == 4) {
            return Country.KOREA;
        } else {
            return Country.CHINA;
        }
    }
}
