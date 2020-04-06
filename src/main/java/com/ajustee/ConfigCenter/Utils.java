package com.ajustee.ConfigCenter;

public final class Utils {
    private Utils() { }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String requireNonEmpty(String value) {
        if (value == null) throw new IllegalArgumentException("The argument must be non-nullable.");
        if (value.isEmpty()) throw new IllegalArgumentException("The argument must be non-empty.");
        return value;
    }

    public static <T> T requireNonNull(T value) {
        if (value == null) throw new IllegalArgumentException("The argument must be non-nullable.");
        return value;
    }
}
