/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.utils;

/**
 * Contain Math related custom function
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public class MathUtil {
    public static Double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}
