package org.example;

import java.util.Arrays;

/**
 * Упрощенная реализация класса StringBuilder (только базовые операции без множественных перегрузок).
 */
public class StrBuilder {
    private int length;

    private int capacity;

    private char[] array;

    private final int BASE_CAPACITY = 16;

    public StrBuilder() {
        length = 0;
        capacity = BASE_CAPACITY;
        array = new char[capacity];
    }

    public StrBuilder(int capacity) throws NegativeArraySizeException {
        if (capacity < 0) {
            throw new NegativeArraySizeException("the capacity argument must not be less than 0");
        }

        length = 0;
        this.capacity = capacity;
        array = new char[capacity];
    }


    public StrBuilder(String string) {
        length = string.length();
        capacity = length + BASE_CAPACITY;
        array = Arrays.copyOf(string.toCharArray(), capacity);
    }

    public int length() {
        return length;
    }

    public int capacity() {
        return capacity;
    }

    public void setLength(int newLength) throws StringIndexOutOfBoundsException {
        if (newLength < 0) {
            throw new StringIndexOutOfBoundsException(newLength);
        }

        if (capacity < newLength) {
            capacity = newLength;
            array = Arrays.copyOf(array, capacity);
        } else if (length > newLength) {
            var newArray = new char[capacity];
            for (int i = 0; i < newLength; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }

        length = newLength;
    }

    public StrBuilder append(String string) {
        var newLength = length + string.length();

        if (newLength <= capacity) {
            copyStringToArray(array, string, length);
        } else {
            capacity = newLength * 2;
            array = Arrays.copyOf(array, capacity);
            copyStringToArray(array, string, length);
        }

        length = newLength;
        return this;
    }

    public StrBuilder insert(int offset, String string) throws StringIndexOutOfBoundsException {
        if (offset < 0 || offset >= length) {
            throw new StringIndexOutOfBoundsException(offset);
        }

        var newLength = length + string.length();

        if (capacity <= newLength) {
            capacity = newLength * 2;
            array = Arrays.copyOf(array, capacity);
        }

        shiftRightArrayElements(array, newLength, string.length());
        copyStringToArray(array, string, offset);
        length = newLength;

        return this;
    }

    public StrBuilder delete(int start, int end) throws StringIndexOutOfBoundsException {
        if (start < 0 || start > length || start > end) {
            throw new StringIndexOutOfBoundsException("start must not be negative, greater than length(), or greater than end");
        }

        if (start == end) {
            return this;
        }

        int diff = end - start;

        for (int i = start; i < end; i++) {
            array[i] = '\u0000';
        }

        for (int i = start; i < length; i++) {
            array[i] = array[i + diff];
        }

        length -= diff;
        return this;
    }

    public void trimToSize() {
        if (length < capacity) {
            capacity = length;
            array = Arrays.copyOf(array, capacity);
        }
    }

    @Override
    public String toString() {
        return new String(array, 0, length);
    }

    private void shiftRightArrayElements(char[] array, int length, int offset) {
        for (int i = length - 1; i >= offset; i--) {
            array[i] = array[i - offset];
        }
    }

    private void copyStringToArray(char[] array, String string, int startIndex) {
        for (int i = startIndex, j = 0; j < string.length(); i++, j++) {
            array[i] = string.charAt(j);
        }
    }
}
