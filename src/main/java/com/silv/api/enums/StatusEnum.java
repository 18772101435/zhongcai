package com.silv.api.enums;

public enum StatusEnum {
    VALID("有效", 1), INVALID("无效", 0);

    private final String name;
    private final int value;

    StatusEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static StatusEnum parse(int value) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
