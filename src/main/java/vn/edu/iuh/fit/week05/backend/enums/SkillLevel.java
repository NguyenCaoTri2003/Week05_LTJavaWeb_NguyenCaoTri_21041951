package vn.edu.iuh.fit.week05.backend.enums;

import java.util.stream.Stream;

public enum SkillLevel {
    MASTER(1), BEGINNER(2), ADVANCED(3), PROFESSIONAL(4), IMTERMEDIATE(5);

    private final int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SkillLevel fromValue(int value) {
        return Stream.of(SkillLevel.values())
                .filter(status -> status.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
