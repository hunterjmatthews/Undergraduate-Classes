/**
 * @author Hunter Matthews - HJM210004
 * @date Due: 11/13/23
 */

// Change to your net id
package hjm210004;

import java.util.*;

public class MDS {
    // Add fields of MDS here
    /* Map of item IDs to their corresponding item objects. */
    TreeMap<Integer, Item> tree;
    /* Map of description IDs to a set of item IDs that contain the description. */
    HashMap<Integer, HashSet<Integer>> table;

    // Constructors
    /**
     * Constructs an MDS object with initialized item and description maps.
     */
    public MDS() {
        tree = new TreeMap<>();
        table = new HashMap<>();
    }

    /* ______________________________________________ ITEM ______________________________________________ */
    /* Represents an item with id, price, and a list of description IDs. */
    static class Item {
        int id;
        int price;
        List<Integer> description;

        /**
         *  Constructs an Item with the given id, price, and description list.
         *
         * @param id The unique identifier of the item.
         * @param price The price of the item.
         * @param description The list of description IDs for the item.
         */
        Item(int id, int price, List<Integer> description) {
            this.id = id;
            this.price = price;
            this.description = new ArrayList<>(description);
        }
    }

    /* _____________________ Public methods of MDS. Do not change their signatures. _____________________ */

    /**
     *  Inserts an item and or updates an existing item's price and description.
     *
     * @param id The unique identifier of the item.
     * @param price The price of the item.
     * @param list The list of description IDs for the item.
     * @return '0' if an existing item was updated.
     *         '1' if a new item was added.
     */
    /*
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated.
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(int id, int price, java.util.List<Integer> list) {
        // Check if the item is new.
        boolean flag = !tree.containsKey(id);
        // If the item is new or has a non-empty description, update the description map.
        if(flag || (list != null && !list.isEmpty())) { descMapUpdate(id, list, flag); }

        // Insert a new item or get the already existing one.
        Item item = tree.get(id);
        if (item == null) {
            item = new Item(id, price, list);
            tree.put(id, item);
        }
        // Update the price of the item.
        item.price = price;
        // Used for handling cases where only the item's price needs to be updated.
        if(!flag && (list == null || list.isEmpty())) { return 0; }
        // Update the description for new or existing items.
        item.description.clear();
        item.description.addAll(list);
        // If existing item was updated, then we return '0'. If new item was added, then we return '1'.
        if(flag) { return 1; } else { return 0; }
    }

    /**
     * Finds the price of the item using the item's unique identifier.
     *
     * @param id The unique identifier of the item.
     * @return The found price of the item, or '0' if item does not exist.
     */
    /* b. Find(id): return price of item with given id (or 0, if not found). */
    public int find(int id) {
        // Attempt to find the item by its unique ID.
        Item item = tree.getOrDefault(id, null);
        // Return the item's price if it exists, otherwise return '0' if it does not exist.
        if(item != null) { return item.price; } else { return 0; }
    }

    /**
     * Deletes an item using the item's unique identifier.
     *
     * @param id The unique identifier of the item.
     * @return The sum of the description IDs of the deleted item, or '0' if the item does not exist.
     */
    /*
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
        // Initialize the sum of the description values.
        int descSum = 0;
        // Attempt to remove the item from the item (tree) map.
        Item item = tree.remove(id);
        // If item was not found, then we return '0'.
        if(item == null) { return 0; }
        // Iterate over each description value of the item
        for(int desc : item.description) {
            // Add description value to the sum.
            descSum += desc;
            // Find and update the set of IDs associated with the description value.
            HashSet<Integer> ids = table.get(desc);
            ids.remove(id);
            // Remove the description from the table if no IDs are associated with it.
            if(ids.isEmpty()) { table.remove(desc); }
        }
        // Returns the sum of the ints that are in the description of the item deleted.
        return descSum;
    }

    /**
     * Finds the minimum price within the items associated with a given descriptor.
     *
     * @param n The descriptor used to find associated items.
     * @return The minimum price of the items, or we return '0' if no associated items and or descriptor exists.
     */
    /*
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public int findMinPrice(int n) {
        // Retrieve the set of item IDs associated with the supplied description.
        HashSet<Integer> ids = table.get(n);
        // If IDs are not found, then we return '0'.
        if(ids == null || ids.isEmpty()) { return 0; }
        // Initialize the minimumPrice with the maximum possible integer value.
        int minimumPrice = Integer.MAX_VALUE;
        // Iterate over each id in the ids collection
        for(Integer id : ids) {
            // Find the item associated with the id.
            Item item = tree.get(id);
            // Update minimumPrice if the current item's price is lower.
            if(item != null && item.price < minimumPrice) { minimumPrice = item.price; }
        }
        // Return the minimum price found or we return '0' if no items were found.
        return (minimumPrice == Integer.MAX_VALUE) ? 0 : minimumPrice;
    }

    /**
     * Finds the maximum price within the items associated with a given descriptor.
     *
     * @param n The descriptor used to find associated items.
     * @return The maximum price of the items, or we return '0' if no associated items and or descriptor exists.
     */
    /*
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
        // Retrieve the set of item IDs associated with the supplied description.
        HashSet<Integer> ids = table.get(n);
        // If IDs are not found, then we return '0'.
        if(ids == null || ids.isEmpty()) { return 0; }
        // Initialize the maximumPrice.
        int maximumPrice = 0;
        // Iterate over each id in the ids collection.
        for(Integer id : ids) {
            Item item = tree.get(id);
            if(item != null && item.price > maximumPrice) { maximumPrice = item.price; }
        }
        // Return the highest price or returns '0' if there is no such item.
        return maximumPrice;
    }

    /**
     * Counts the number of items that fall within a specific range using a specified description.
     *
     * @param n The description ID we want to use to find the associated item(s).
     * @param low The lower bound.
     * @param high The upper bound.
     * @return The count of items that fall within the specified range.
     */
    /*
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
        // Retrieve the set of item IDs associated with the supplied description.
        HashSet<Integer> ids = table.get(n);
        // If IDs are not found, then we return '0'.
        if(ids == null || ids.isEmpty()) { return 0; }
        // Initialize the counter for the number of items within the specified price range.
        int counter = 0;
        // Iterate over each id in the ids collection.
        for(Integer id : ids) {
            // Find the item associated with the unique ID.
            Item item = tree.get(id);
            // Increment the counter if the item's price falls within the specified range.
            if(item != null && item.price >= low && item.price <= high) { counter++; }
        }
        // Return the total count of items in the specified range.
        return counter;
    }

    /**
     * Removes a list of descriptions from an item and updates mappings.
     *
     * @param id The unique identifier of the item.
     * @param list A list of description IDs (that we want to remove)
     * @return The sum of the description IDs that were removed, or '0' none were removed.
     */
    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
        // Initialize 'delSum' to keep track of removed descriptions.
        int delSum = 0;
        // Find the item from the item (tree) map using the item's unique ID.
        Item item = tree.get(id);
        // If the item does not exist or the list is null and or empty, then we return '0'.
        if(item == null || list == null || list.isEmpty()) { return 0; }
        // Iterate through each description in the list.
        for(int desc : list) {
            // Check if the item's description contains the current description.
            if(item.description.contains(desc)) {
                // Add the item's description to the 'delSum'.
                delSum += desc;
                // Get the set of item IDs associated with current description.
                HashSet<Integer> ids = table.get(desc);
                // Remove the current item ID from the set.
                ids.remove(id);
                // If no more items exists with this description, remove the description from the table.
                if(ids.isEmpty()) { table.remove(desc); }
            }
        }
        // Remove all descriptions in the list from the item's description.
        item.description.removeAll(list);
        // Return the sum of the removed descriptions.
        return delSum;
    }

    /*____________________ Helper Methods ____________________*/

    /**
     * Updates the description map with new and or updated descriptions for the item.
     *
     * @param id The unique identifier of the item.
     * @param list The list of description IDs for the item.
     * @param flag Flag indicating if the item is new.
     */
    private void descMapUpdate(int id, java.util.List<Integer> list, boolean flag) {
        // If the item is not a new item, then we need to remove its old descriptions.
        if(!flag) { descMapRemove(id); }
        // Iterate through each description in the list, then if the description is not in the table, we need to initialize a new set and add the ID.
        for(int desc : list) {
            if (!table.containsKey(desc)) { table.put(desc, new HashSet<>()); }
            Set<Integer> set = table.get(desc);
            set.add(id);
        }
    }

    /**
     * Removes the given item's old description from the description map.
     *
     * @param id The unique identifier of the item.
     */
    private void descMapRemove(int id) {
        // Find the existing item from the item (tree) map using the item's unique ID.
        Item oldItem = tree.get(id);
        // Iterate through each description in the item's description.
        for(int desc : oldItem.description) {
            // Retrieve the set of item IDs associated with the current description.
            HashSet<Integer> ids = table.get(desc);
            // Remove the current item ID from the ids set.
            ids.remove(id);
            // If no more items exist with this description, remove the description from the table.
            if(ids.isEmpty()) { table.remove(desc); }
        }
    }
}
