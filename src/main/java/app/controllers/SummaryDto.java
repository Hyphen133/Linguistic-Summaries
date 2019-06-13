package app.controllers;

import lombok.Builder;

@Builder
public class SummaryDto {
    private String summary;
    private double goodness;
    private double t1;
    private double t2;
    private double t3;
    private double t4;
    private double t5;
    private double t6;
    private double t7;
    private double t8;
    private double t9;
    private double t10;
    private double t11;

    public String getSummary() {
        return summary;
    }

    public double getGoodness() {
        return goodness;
    }

    public double getT1() {
        return t1;
    }

    public double getT2() {
        return t2;
    }

    public double getT3() {
        return t3;
    }

    public double getT4() {
        return t4;
    }

    public double getT5() {
        return t5;
    }

    public double getT6() {
        return t6;
    }

    public double getT7() {
        return t7;
    }

    public double getT8() {
        return t8;
    }

    public double getT9() {
        return t9;
    }

    public double getT10() {
        return t10;
    }

    public double getT11() {
        return t11;
    }

    @Override
    public String toString() {
        return "SummaryDto{" +
                "summary=" + summary +
                ", goodness=" + goodness +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                ", t4=" + t4 +
                ", t5=" + t5 +
                ", t6=" + t6 +
                ", t7=" + t7 +
                ", t8=" + t8 +
                ", t9=" + t9 +
                ", t10=" + t10 +
                ", t11=" + t11 +
                '}';
    }
}
