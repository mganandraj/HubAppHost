/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.utilities.java;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Helper class that contains different utilities related to lists
 */
public final class ListUtils {
    private ListUtils() {
        throw new UtilityInstantiationException();
    }

    /**
     * Gets if a list is null or empty
     *
     * @param list list
     * @return true if list is null or empty and false otherwise
     */
    public static boolean isListNullOrEmpty(@Nullable List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean hasItems(List<?> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * This method will compare the List and their content to check if the are the same or different
     * been the same meaning that both their size are the same, the elements are the same, and they are in the same order.
     * <p>
     * ex. 1, 2, 3 is the same as 1, 2, 3   BUT not the same to 2, 3, 1
     * ex. 1, 2, A is not the same to 1, 2, A, null
     *
     * @param leftList  list to compare
     * @param rightList another list to compare
     * @param <U>       the type of elements in the left List
     * @param <V>       the type of elements in the right List
     * @return true if they are the same, false otherwise
     */
    public static <U, V> boolean areSame(@Nullable List<U> leftList, @Nullable List<V> rightList) {
        // One is null the other is not
        if ((leftList == null || rightList == null) && leftList != rightList) {
            return false;
        }

        // both are NULL
        if (leftList == null) {
            return true;
        }

        // Different amount of items in both lists
        int leftListSize = leftList.size();
        int rightListSize = rightList.size();
        if (leftListSize != rightListSize) {
            return false;
        }

        for (int i = 0; i < leftListSize; i++) {
            U leftElement = leftList.get(i);
            V rightElement = rightList.get(i);

            // The items at this position are NOT equal
            if ((leftElement == null && rightElement != null) || (leftElement != null && !leftElement.equals(rightElement))) {
                return false;
            }
        }

        // They are the same
        return true;
    }

    /**
     * Moves all the items starting at {@code startingIndex} and ending at the end of the {@code origin} list, to the {@code destination} list.
     * Ex.
     * origin = [1, 2, 3, 4, 5] AND destination = [6, 7, 8] if we execute moveItemsToDifferentList(origin, destination, 3) we would obtain
     * origin = [3, 4, 5] AND destination = [6, 7, 8, 1, 2]
     * <p>
     * Ex.
     * origin = [1, 2, 3, 4, 5] AND destination = [6, 7, 8] if we execute moveItemsToDifferentList(origin, destination, 0) we would obtain
     * origin = [] AND destination = [6, 7, 8, 1, 2, 3, 4, 5]
     *
     * @param origin                 the list in which we are going to remove all of the items except the last {@code numberOfElementsToKeep}
     * @param destination            the list in which all removed items from the {@code origin} will be placed
     * @param numberOfElementsToKeep the maximum amount of items that we will keep in the {@code origin}
     * @param <T>                    the type of objects in {@code origin} and {@code destination}
     */
    public static <T> void moveItemsToDifferentList(@NonNull List<T> origin, @NonNull List<T> destination, @IntRange(from = 0) int numberOfElementsToKeep) {
        // If the {@code origin} is empty or it has less items than {@code numberOfElementsToKeep}, there's nothing to do
        if (origin.isEmpty() || origin.size() <= numberOfElementsToKeep) {
            return;
        }

        // Rotates the {@code origin} so that we move the items to remove to the end of the list, to avoid shifting while removing
        Collections.rotate(origin, numberOfElementsToKeep);

        // We remove one by one the items from the {@code origin} and we push them to the end of the {@code destination}
        int missingRemovals = origin.size() - numberOfElementsToKeep;
        int destinationOriginalSize = destination.size();
        while (missingRemovals-- > 0) {
            T removed = origin.remove(numberOfElementsToKeep + missingRemovals);
            destination.add(removed);
        }

        // Reverses the added items because they were added in reverse order
        List<T> itemsThatWhereAdded = destination.subList(destinationOriginalSize, destination.size());
        Collections.reverse(itemsThatWhereAdded);
    }


    /**
     * Create a list with objects dropping null values
     * @param objects array of objects
     * @param <T> Any object
     * @return List of non null objects.
     */
    @SafeVarargs
    public static <T> List<T> addIfNotNull(T... objects) {
        ArrayList<T> list = new ArrayList<>();
        for (T object : objects) {
            if (object != null) {
                list.add(object);
            }
        }
        return list;
    }

}