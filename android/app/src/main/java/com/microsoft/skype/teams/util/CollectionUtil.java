package com.microsoft.skype.teams.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/***
 * Helper class for Collection related utilities
 */
public final class CollectionUtil {
    @NonNull
    public static <C> List<C> asList(@NonNull SparseArrayCompat<C> sparseArray) {
        List<C> arrayList = new ArrayList<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }

        return arrayList;
    }

    @NonNull
    public static <C> List<C> asLinkedList(@NonNull SparseArrayCompat<C> sparseArray) {
        List<C> linkedList = new LinkedList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            linkedList.add(sparseArray.valueAt(i));
        }

        return linkedList;
    }

    public static <T> boolean isSubset(@Nullable Collection<T> supersetCollection, @Nullable Collection<T> subsetCollection) {
        if (subsetCollection == null) {
            return true;
        }

        if (supersetCollection == null) {
            return false;
        }

        for (T subsetElement : subsetCollection) {
            if (subsetElement == null) {
                continue;
            }

            boolean foundSubsetElement = false;
            for (T supersetElement : supersetCollection) {
                if (supersetElement.equals(subsetElement)) {
                    foundSubsetElement = true;
                    break;
                }
            }

            if (!foundSubsetElement) {
                return false;
            }
        }

        return true;
    }

    public static <T> boolean isSubset(@Nullable T[] supersetArray, @Nullable T[] subsetArray) {
        return subsetArray == null
               || (supersetArray != null
                   && isSubset(Arrays.asList(supersetArray), Arrays.asList(subsetArray)));
    }

    public static <T> boolean arrayContains(@Nullable T[] array, @Nullable T itemToCheck) {
        if (itemToCheck == null || array == null) {
            return false;
        }

        for (T item : array) {
            if (itemToCheck.equals(item)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCollectionEmpty(@Nullable Collection collection) {
        return collection == null || collection.isEmpty();
    }

    private CollectionUtil() {
    }
}
