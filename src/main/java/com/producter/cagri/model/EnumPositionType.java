package com.producter.cagri.model;

public enum EnumPositionType {
    PG("POINT_GUARD"),
    SG("SHOOTING_GUARD"),
    SF("SMALL_FORWARD"),
    PF("POWER_FORWARD"),
    C("CENTER");

    private final String label;

    private EnumPositionType(String label){
        this.label = label;
    }
}
