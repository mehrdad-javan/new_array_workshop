package se.lexicon;

import java.util.Arrays;
// JavaDoc: The JavaDoc comments provide comprehensive documentation for each method,
// explaining their purpose, parameters, return values, and possible exceptions.
// These comments help improve code readability and enable better understanding of the class and its methods for other developers.

/**
 * The NameRepository class provides methods to manage a list of names in an array.
 */
public class NameRepository {

    private static String[] names = new String[0];

    /**
     * Get the current size of the names array.
     *
     * @return The number of elements in the names array.
     */
    public static int getSize() {
        return names.length;
    }

    /**
     * Set the names array to the provided array of names.
     *
     * @param names The array of names to set.
     */
    public static void setNames(String[] names) {
        NameRepository.names = names;
    }

    /**
     * Clear the names array by creating a new empty array.
     */
    public static void clear() {
        names = new String[0];
    }

    /**
     * Get a copy of the names array.
     *
     * @return A new array containing all elements from the names array.
     */
    public static String[] findAll() {
        return Arrays.copyOf(names, names.length);
    }

    /**
     * Find a name that matches the given fullName case-insensitively.
     *
     * @param fullName The full name to search for.
     * @return The matching name if found; otherwise, null.
     */
    public static String find(String fullName) {
        for (String element : names) {
            if (element.equalsIgnoreCase(fullName)) {
                return element;
            }
        }
        return null;
    }

    /**
     * Add a new fullName to the names array if it doesn't already exist.
     *
     * @param fullName The full name to add.
     * @return True if the fullName is added successfully; false if it already exists.
     */
    public static boolean add(String fullName) {
        if (find(fullName) != null) {
            return false;
        }

        String[] newArray = Arrays.copyOf(names, names.length + 1);
        newArray[newArray.length - 1] = fullName;
        names = newArray;

        return true;
    }

    /**
     * Find all names that match the given firstName.
     *
     * @param firstName The first name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByFirstName(String firstName) {
        String[] result = new String[0];
        for (String element : names) {
            String[] fullNameArray = element.split(" ");
            String fn = fullNameArray[0];

            if (fn.equalsIgnoreCase(firstName)) {
                result = addToArray(result, element);
            }
        }
        return result;
    }

    /**
     * Find all names that match the given lastName.
     *
     * @param lastName The last name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByLastName(String lastName) {
        String[] result = new String[0];
        for (String element : names) {
            String[] fullNameArray = element.split(" ");
            String ln = fullNameArray[1];

            if (ln.equalsIgnoreCase(lastName)) {
                result = addToArray(result, element);
            }
        }
        return result;
    }

    /**
     * Update a name in the names array from the original name to the updated name.
     *
     * @param original    The original name to update.
     * @param updatedName The updated name to set.
     * @return True if the name is updated successfully; false if the updated name already exists or the original name is not found.
     */
    public static boolean update(String original, String updatedName) {
        int originalIndex = indexOf(names, original);
        if (originalIndex == -1) {
            return false;
        }

        int updatedIndex = indexOf(names, updatedName);
        if (updatedIndex != -1) {
            return false;
        }

        names[originalIndex] = updatedName;
        return true;
    }

    /**
     * Get the index of a string in the given array, ignoring case sensitivity.
     *
     * @param array  The array to search in.
     * @param string The string to search for.
     * @return The index of the string if found; otherwise, -1.
     */
    private static int indexOf(String[] array, String string) {
        for (int i = 0; i < array.length; i++) {
            String content = array[i];
            if (content.equalsIgnoreCase(string)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove a name from the names array, case-insensitively.
     *
     * @param fullName The full name to remove.
     * @return True if the name is removed successfully; false if the name is not found in the array.
     */
    public static boolean remove(String fullName) {
        fullName = fullName.toLowerCase();
        int findIndex = -1;
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().equals(fullName)) {
                findIndex = i;
                break;
            }
        }

        if (findIndex == -1) {
            return false;
        }

        String[] anotherArray = new String[names.length - 1];
        int sequencer = 0;
        for (int i = 0; i < names.length; i++) {
            if (i == findIndex) {
                continue;
            }
            anotherArray[sequencer++] = names[i];
        }
        names = anotherArray;
        return true;
    }

    /**
     * Add a new name to the source array and return a new array.
     *
     * @param source  The source array.
     * @param newName The new name to add to the array.
     * @return A new array with the new name added at the end.
     */
    private static String[] addToArray(String[] source, String newName) {
        String[] tmp = Arrays.copyOf(source, source.length + 1);
        tmp[tmp.length - 1] = newName;
        return tmp;
    }
}