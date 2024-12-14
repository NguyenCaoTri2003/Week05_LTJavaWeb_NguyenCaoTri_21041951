package vn.edu.iuh.fit.week05.backend.enums;

public enum SkillType {
    SOFT_SKILL(1), UNSPECIFIED(0), TECHNICAL_SKILL(-1);
    private int value;

    private SkillType(int value) {
        this.value = value;
    }

    public static SkillType getType(int value){
        if (value == 1) {
            return SkillType.SOFT_SKILL;
        } else if (value == 0) {
            return SkillType.UNSPECIFIED;
        } else {
            return SkillType.TECHNICAL_SKILL;
        }
    }
}
