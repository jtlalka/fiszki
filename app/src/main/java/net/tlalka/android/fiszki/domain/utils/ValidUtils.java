package net.tlalka.android.fiszki.domain.utils;

import java.util.List;
import java.util.Map;

public class ValidUtils {

    private ValidUtils() {
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isTrue(Boolean value) {
        return isNotNull(value) && value;
    }

    public static boolean isFalse(Boolean value) {
        return isNotNull(value) && !value;
    }

    public static boolean isEmpty(String value) {
        return isNotNull(value) && value.length() == 0;
    }

    public static boolean isEmpty(List value) {
        return isNotNull(value) && value.isEmpty();
    }

    public static boolean isEmpty(Map value) {
        return isNotNull(value) && value.isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return isNotNull(value) && value.length() > 0;
    }

    public static boolean isNotEmpty(List value) {
        return isNotNull(value) && !value.isEmpty();
    }

    public static boolean isNotEmpty(Map value) {
        return isNotNull(value) && !value.isEmpty();
    }
}
