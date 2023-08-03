package se.lexicon;

import java.util.Arrays;

public class NameRepository {

    private static String[] names = new String[0];

    public static int getSize() {
        return names.length;
    }

    public static void setNames(String[] names) {
        NameRepository.names = names;
    }

    public static void clear() {
        names = new String[0];
    }

    public static String[] findAll() {
        return Arrays.copyOf(names, names.length);
    }

    public static String find(String fullName) {
        for (String element : names) {
            if (element.equalsIgnoreCase(fullName)) {
                return element;
            }
        }
        return null;
    }

    public static boolean add(String fullName) {
        if (find(fullName) != null) {
            return false;
        }

        String[] newArray = Arrays.copyOf(names, names.length + 1);
        newArray[newArray.length - 1] = fullName;
        names = newArray;

        return true;
    }

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

    private static int indexOf(String[] array, String string) {
        for (int i = 0; i < array.length; i++) {
            String content = array[i];
            if (content.equalsIgnoreCase(string)) {
                return i;
            }
        }
        return -1;
    }

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

    private static String[] addToArray(String[] source, String newName) {
        String[] tmp = Arrays.copyOf(source, source.length + 1);
        tmp[tmp.length - 1] = newName;
        return tmp;
    }
}