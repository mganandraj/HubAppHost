/*
 * Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.teams.androidutils;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.IntDef;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class containing utility methods for Android Parcel
 */
public final class ParcelUtilities {
    /**
     * Writes an object to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param object Object to logAppLifecycle to the parcel
     */
    public static void writeObject(Parcel out, Object object) {
        if (out == null) {
            return;
        }

        @ParcelObjectType
        int type = getObjectType(object);
        if (type == ParcelObjectType.UNKNOWN) {
            throw new IllegalArgumentException("Can't parcel an object of type unknown. The object is of type "
                    + object.getClass().getCanonicalName());
        }

        out.writeInt(type);
        switch (type) {
            case ParcelObjectType.BYTE:
                out.writeByte((Byte) object);
                break;
            case ParcelObjectType.INTEGER:
                out.writeInt((Integer) object);
                break;
            case ParcelObjectType.LONG:
                out.writeLong((Long) object);
                break;
            case ParcelObjectType.STRING:
                writeString(out, (String) object);
                break;
            case ParcelObjectType.FLOAT:
                out.writeFloat((Float) object);
                break;
            case ParcelObjectType.DOUBLE:
                out.writeDouble((Double) object);
                break;
            case ParcelObjectType.BOOLEAN:
                writeBoolean(out, (Boolean) object);
                break;
            case ParcelObjectType.ARRAY_OF_BYTE:
                writeByteArray(out, (byte[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_INTEGER:
                writeIntArray(out, (int[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_LONG:
                writeLongArray(out, (long[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_STRING:
                writeStringArray(out, (String[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_FLOAT:
                writeFloatArray(out, (float[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_DOUBLE:
                writeDoubleArray(out, (double[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_BOOLEAN:
                writeBooleanArray(out, (boolean[]) object);
                break;
            case ParcelObjectType.ARRAY_OF_OBJECT:
                writeObjectArray(out, (Object[]) object);
                break;
            case ParcelObjectType.MAP:
                writeStringMap(out, (Map<String, Object>) object);
                break;
            case ParcelObjectType.SERIALIZABLE:
                out.writeSerializable((Serializable) object);
                break;
            case ParcelObjectType.PARCELABLE:
                out.writeParcelable((Parcelable) object, 0 /*flags*/);
                break;
            case ParcelObjectType.NULL:
            case ParcelObjectType.UNKNOWN:
            default:
                break;
        }
    }

    /**
     * Reads an object from the parcel
     *
     * @param in Source parcel
     */
    public static Object readObject(Parcel in) {
        if (in == null) {
            return null;
        }

        @ParcelObjectType
        int type = in.readInt();
        switch (type) {
            case ParcelObjectType.BYTE:
                return in.readByte();
            case ParcelObjectType.INTEGER:
                return in.readInt();
            case ParcelObjectType.LONG:
                return in.readLong();
            case ParcelObjectType.STRING:
                return readString(in);
            case ParcelObjectType.FLOAT:
                return in.readFloat();
            case ParcelObjectType.DOUBLE:
                return in.readDouble();
            case ParcelObjectType.BOOLEAN:
                return readBoolean(in);
            case ParcelObjectType.ARRAY_OF_BYTE:
                return readByteArray(in);
            case ParcelObjectType.ARRAY_OF_INTEGER:
                return readIntArray(in);
            case ParcelObjectType.ARRAY_OF_LONG:
                return readLongArray(in);
            case ParcelObjectType.ARRAY_OF_STRING:
                return readStringArray(in);
            case ParcelObjectType.ARRAY_OF_FLOAT:
                return readFloatArray(in);
            case ParcelObjectType.ARRAY_OF_DOUBLE:
                return readDoubleArray(in);
            case ParcelObjectType.ARRAY_OF_BOOLEAN:
                return readBooleanArray(in);
            case ParcelObjectType.ARRAY_OF_OBJECT:
                return readObjectArray(in);
            case ParcelObjectType.MAP:
                return readStringMap(in);
            case ParcelObjectType.SERIALIZABLE:
                return in.readSerializable();
            case ParcelObjectType.PARCELABLE:
                return in.readParcelable(ParcelUtilities.class.getClassLoader());
            case ParcelObjectType.NULL:
            case ParcelObjectType.UNKNOWN:
            default:
                return null;
        }
    }

    public static void writeBoolean(Parcel out, Boolean booleanValue) {
        if (out != null) {
            out.writeInt(booleanValue ? 1 : 0);
        }
    }

    /**
     * Reads an object from the parcel
     *
     * @param in Source parcel
     */
    public static boolean readBoolean(Parcel in) {
        return in != null && in.readInt() != 0;
    }

    /**
     * Reads a Map with String keys and Object values from a parcel
     *
     * @param in Source parcel
     */
    public static Map<String, Object> readStringMap(Parcel in) {
        if (in == null) {
            return null;
        }

        int mapSize = in.readInt();
        if (mapSize > 0) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < mapSize; i++) {
                map.put(readString(in), readObject(in));
            }

            return map;
        }

        return null;
    }

    /**
     * Write a Map with String keys and Object values to a parcel
     *
     * @param out destination parcel
     * @param map Map to logAppLifecycle to the the parcel
     */
    public static void writeStringMap(Parcel out, Map<String, Object> map) {
        if (out == null) {
            return;
        }

        int size = map == null ? 0 : map.size();
        out.writeInt(size);
        if (size > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                writeString(out, entry.getKey());
                writeObject(out, entry.getValue());
            }
        }
    }

    /**
     * Reads a string from an input Parcel
     */
    public static String readString(Parcel in) {
        int stringLength = in.readInt();
        if (stringLength == -1) {
            return null;
        }

        return stringLength > 0 ? in.readString() : "";
    }

    /**
     * Writes a string to a destination Parcel
     */
    public static void writeString(Parcel out, String string) {
        int stringLength = string == null ? -1 : string.length();
        out.writeInt(stringLength);
        if (stringLength > 0) {
            out.writeString(string);
        }
    }

    /**
     * Writes a byte array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeByteArray(Parcel out, byte[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeByteArray(array);
        }
    }

    /**
     * Writes an integer array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeIntArray(Parcel out, int[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeIntArray(array);
        }
    }

    /**
     * Writes a long array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeLongArray(Parcel out, long[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeLongArray(array);
        }
    }

    /**
     * Writes a string array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeStringArray(Parcel out, String[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeStringArray(array);
        }
    }

    /**
     * Writes a float array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeFloatArray(Parcel out, float[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeFloatArray(array);
        }
    }

    /**
     * Writes a double array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeDoubleArray(Parcel out, double[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeDoubleArray(array);
        }
    }

    /**
     * Writes a boolean array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    public static void writeBooleanArray(Parcel out, boolean[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            out.writeBooleanArray(array);
        }
    }

    /**
     * Reads a byte array from the parcel
     *
     * @param in Source parcel
     */
    public static byte[] readByteArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            byte[] array = new byte[arraySize];
            in.readByteArray(array);
            return array;
        }

        return null;
    }

    /**
     * Reads an integer array from the parcel
     *
     * @param in Source parcel
     */
    public static int[] readIntArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            int[] array = new int[arraySize];
            in.readIntArray(array);
            return array;
        }

        return null;
    }

    /**
     * Reads a long array from the parcel
     *
     * @param in Source parcel
     */
    public static long[] readLongArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            long[] array = new long[arraySize];
            in.readLongArray(array);
            return array;
        }

        return null;
    }

    /**
     * Reads a string array from the parcel
     *
     * @param in Source parcel
     */
    public static String[] readStringArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            String[] array = new String[arraySize];
            in.readStringArray(array);
            return array;
        }

        return null;
    }

    /**
     * Reads a float array from the parcel
     *
     * @param in Source parcel
     */
    public static float[] readFloatArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            float[] array = new float[arraySize];
            in.readFloatArray(array);
            return array;
        }

        return null;
    }

    /**
     * Reads a double array from the parcel
     *
     * @param in Source parcel
     */
    public static double[] readDoubleArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            double[] array = new double[arraySize];
            in.readDoubleArray(array);
            return array;
        }

        return null;
    }

    /**
     * Reads a boolean array from the parcel
     *
     * @param in Source parcel
     */
    public static boolean[] readBooleanArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            boolean[] array = new boolean[arraySize];
            in.readBooleanArray(array);
            return array;
        }

        return null;
    }

    /**
     * Writes an object array to the parcel
     *
     * @param out Destination parcel to logAppLifecycle to
     * @param array Array to logAppLifecycle to the parcel
     */
    private static void writeObjectArray(Parcel out, Object[] array) {
        if (out == null) {
            return;
        }

        int arraySize = array == null ? 0 : array.length;
        out.writeInt(arraySize);
        if (arraySize > 0) {
            for (Object object : array) {
                writeObject(out, object);
            }
        }
    }

    /**
     * Reads an object array from the parcel
     *
     * @param in Source parcel
     */
    private static Object readObjectArray(Parcel in) {
        if (in == null) {
            return null;
        }

        int arraySize = in.readInt();
        if (arraySize > 0) {
            Object[] array = new Object[arraySize];
            for (int i = 0; i < arraySize; i++) {
                array[i] = readObject(in);
            }

            return array;
        }

        return null;
    }

    /**
     * Gets the parcelable object type for an object
     */
    @ParcelObjectType
    private static int getObjectType(Object obj) {
        if (obj == null) {
            return ParcelObjectType.NULL;
        } else if (obj instanceof Integer) {
            return ParcelObjectType.INTEGER;
        } else if (obj instanceof Long) {
            return ParcelObjectType.LONG;
        } else if (obj instanceof Byte) {
            return ParcelObjectType.BYTE;
        } else if (obj instanceof String) {
            return ParcelObjectType.STRING;
        } else if (obj instanceof Float) {
            return ParcelObjectType.FLOAT;
        } else if (obj instanceof Double) {
            return ParcelObjectType.DOUBLE;
        } else if (obj instanceof Boolean) {
            return ParcelObjectType.BOOLEAN;
        } else if (obj instanceof Integer[]) {
            return ParcelObjectType.ARRAY_OF_INTEGER;
        } else if (obj instanceof Byte[]) {
            return ParcelObjectType.ARRAY_OF_BYTE;
        } else if (obj instanceof String[]) {
            return ParcelObjectType.ARRAY_OF_STRING;
        } else if (obj instanceof Float[]) {
            return ParcelObjectType.ARRAY_OF_FLOAT;
        } else if (obj instanceof Double[]) {
            return ParcelObjectType.ARRAY_OF_DOUBLE;
        } else if (obj instanceof Boolean[]) {
            return ParcelObjectType.ARRAY_OF_BOOLEAN;
        } else if (obj instanceof Long[]) {
            return ParcelObjectType.ARRAY_OF_LONG;
        } else if (obj instanceof Object[]) {
            return ParcelObjectType.ARRAY_OF_OBJECT;
        } else if (obj instanceof Map) {
            return ParcelObjectType.MAP;
        } else if (obj instanceof Serializable) {
            return ParcelObjectType.SERIALIZABLE;
        } else if (obj instanceof Parcelable) {
            return ParcelObjectType.PARCELABLE;
        }

        return ParcelObjectType.UNKNOWN;
    }

    private ParcelUtilities() {
    }

    /**
     * Int def to represent different parcelable object types
     */
    @IntDef({ParcelObjectType.UNKNOWN, ParcelObjectType.BYTE, ParcelObjectType.INTEGER, ParcelObjectType.LONG, ParcelObjectType.STRING, ParcelObjectType.FLOAT,
            ParcelObjectType.DOUBLE, ParcelObjectType.BOOLEAN, ParcelObjectType.ARRAY_OF_BYTE, ParcelObjectType.ARRAY_OF_INTEGER, ParcelObjectType.ARRAY_OF_LONG,
            ParcelObjectType.ARRAY_OF_STRING, ParcelObjectType.ARRAY_OF_FLOAT, ParcelObjectType.ARRAY_OF_DOUBLE, ParcelObjectType.ARRAY_OF_BOOLEAN,
            ParcelObjectType.ARRAY_OF_OBJECT, ParcelObjectType.MAP, ParcelObjectType.SERIALIZABLE, ParcelObjectType.NULL, ParcelObjectType.PARCELABLE})
    @Retention(RetentionPolicy.SOURCE)
    @interface ParcelObjectType {
        int UNKNOWN = 0;
        int BYTE = 1;
        int INTEGER = 2;
        int LONG = 3;
        int STRING = 4;
        int FLOAT = 5;
        int DOUBLE = 6;
        int BOOLEAN = 7;
        int ARRAY_OF_BYTE = 8;
        int ARRAY_OF_INTEGER = 9;
        int ARRAY_OF_LONG = 10;
        int ARRAY_OF_STRING = 11;
        int ARRAY_OF_FLOAT = 12;
        int ARRAY_OF_DOUBLE = 13;
        int ARRAY_OF_BOOLEAN = 14;
        int ARRAY_OF_OBJECT = 15;
        int MAP = 16;
        int SERIALIZABLE = 17;
        int NULL = 18;
        int PARCELABLE = 19;
    }

}