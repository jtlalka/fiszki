package net.tlalka.android.fwork;

import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FworkInit {

	public static final String FRAMEWORK_NAME = "FWORK";
	public static final String FRAMEWORK_VERS = "1.0.0";

	public static final class Manager {
		private static Map<String, Object> OBJECT = new HashMap<String, Object>();

		public static void add(Object object) {
			if (Manager.has(object.getClass()) == false) {
				Manager.OBJECT.put(object.getClass().getName(), object);
			} else {
				Log.self("Manager can't override class: " + object.getClass().getName());
			}
		}

		public static boolean del(Class<?> classVal) {
			if (Manager.has(classVal) == true) {
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
			return (value == true);
		}

		public static boolean isFalse(Boolean value) {
			return (value == false);
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
				Log.self("Valid can't check class: " + value.getClass().getName());
				return false;
			}
		}
		
		public static boolean isNotEmpty(Object value) {
			return (isEmpty(value) == false);
		}
	}

	public static final class Log {
		public static void error(Exception ex, String message) {
			Log.show(System.err, ex, "ERROR", message, ex.toString());
		}

		public static void info(String message) {
			Log.show(System.out, null, "INFO", message);
		}

		protected static void self(String...message) {
			Log.show(System.err, null, FRAMEWORK_NAME, message);
		}

		private static void show(PrintStream how, Exception ex, String who, String...what) {
			for (String msg : what) {
				how.println("[" + new Date().toString() + "] " + who + " [" + msg + "]");
			}
			if (ex != null) {
				ex.printStackTrace(how);
			}
		}
	}
}
