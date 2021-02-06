/*
 *  Copyright © Microsoft Corporation. All rights reserved.
 */

package com.microsoft.skype.teams.storage.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// import com.microsoft.skype.teams.storage.tables.UserPreferences;

import java.util.Collection;

/**
 * Interface for {@link UserPreferences} Dao. this will be implemented by each DB implementation.
 *
 * @param <T> type the table stores
 */
public interface IBaseDao<T> {
    /**
     * Delete the entire table of the item passed in.
     * TODO: Transition to deleteAll() that takes no parameters
     */
    void deleteTable(T table);

    /**
     * Delete a single item
     */
    void delete(@NonNull T item);

    /**
     * Save a single item. This inserts the item if it doesn't exist and updates it otherwise
     */
    void save(@NonNull T item);

    /**
     * Save all the items in a transaction
     */
    void saveAllInTransaction(@Nullable Collection<T> items);

    /**
     * Save all the items but do not wrap in the transaction.
     * This should be used only if caller is already running under a transaction!
     */
    void saveAllWithoutTransaction(@Nullable Collection<T> items);

    /**
     * Update an item
     * TODO: Consider removing this method in preference to save(), which does essentially the same thing
     */
    void update(@NonNull T item);
}
