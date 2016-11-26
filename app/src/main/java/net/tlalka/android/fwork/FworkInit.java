package net.tlalka.android.fwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FworkInit {

    public static final String FRAMEWORK_NAME = "FWORK";
    public static final String FRAMEWORK_VERS = "1.0.0";

    public static final class Manager {
        private static Map<String, Object> OBJECT = new HashMap<>();

        public static void set(Object object) {
            Manager.OBJECT.put(object.getClass().getName(), object);
        }

        public static boolean del(Class<?> classVal) {
            if (Manager.has(classVal)) {
                Manager.OBJECT.put(classVal.getName(), null);
                Manager.OBJECT.remove(classVal.getName());
                return true;
            }
            return false;
        }

        public static boolean has(Class<?> classVal) {
            return Manager.OBJECT.containsKey(classVal.getName());
        }

        public static Object get(Class<Object> classVal) {
            return Manager.OBJECT.get(classVal.getName());
        }
    }

    public static final class Register {
        private static Map<String, Object> STORAGE = new HashMap<String, Object>();

        public static boolean has(String key) {
            return Register.STORAGE.containsKey(key);
        }

        public static void add(String key, Object val) {
            Register.STORAGE.put(key, val);
        }

        public static Object get(String key) {
            return Register.STORAGE.get(key);
        }

        public static void del(String key) {
            Register.STORAGE.remove(key);
        }
    }

    public static final class Valid {
        public static boolean isNull(Object value) {
            return (value == null);
        }

        public static boolean isNotNull(Object value) {
            return (value != null);
        }

        public static boolean isTrue(Boolean value) {
            return value;
        }

        public static boolean isFalse(Boolean value) {
            return !value;
        }

        public static boolean isEmpty(Object value) {
            if (isNull(value)) {
                return true;
            }

            if (value instanceof Number) {
                return ((Number) value).doubleValue() == 0;
            } else if (value instanceof CharSequence) {
                return ((String) value).length() == 0;
            } else if (value instanceof List) {
                return ((List<?>) value).size() == 0;
            } else if (value instanceof Map) {
                return ((Map<?, ?>) value).size() == 0;
            } else {
                return false;
            }
        }

        public static boolean isNotEmpty(Object value) {
            return !isEmpty(value);
        }
    }
}
