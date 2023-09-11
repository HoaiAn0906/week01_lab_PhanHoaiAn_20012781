package com.www.week1.week01_lab_phanhoaian_20012781.models;

public enum Grant {
    DISABLED(1),
    ENABLED(0);

    private final int code;

    private Grant(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Grant fromCode(int code) {
        for (Grant grant : Grant.values()) {
            if (grant.getCode() == code) {
                return grant;
            }
        }
        throw new IllegalArgumentException("Invalid AccountStatus code: " + code);
    }
}
