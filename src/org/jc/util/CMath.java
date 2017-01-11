package org.jc.util;

public class CMath {

    public static double redondear(double numero, double decimal) {
        double factor = Math.pow(10.0D, decimal);
        return Math.round(numero * factor) / factor;
    }
}