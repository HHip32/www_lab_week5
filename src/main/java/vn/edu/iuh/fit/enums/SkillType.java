package vn.edu.iuh.fit.enums;

public enum SkillType {
    UNSPECIFIC(0),

    TECHNICAL_SKILL(1),

    SOFT_SKILL(2);
    private int value;

    SkillType(int value) {
    }
    public int getValue(){
        return value;
    }
}
