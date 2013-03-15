package uk.org.alienscience.hammer.conversion;

import java.util.Iterator;
import java.util.List;

/**
 * Converts lists to strings
 */
public class ToString implements Iterable<String> {

    private final Iterator<List<String>> listIterator;

    private ToString(Iterable<List<String>> listIterator) {
        this.listIterator = listIterator.iterator();
    }

    /**
     * Convert lists of strings into strings
     * @param listIterator Iterator through lists of strings
     * @return A string iterator
     */
    public static Iterable<String> flatten(Iterable<List<String>> listIterator) {
        return new ToString(listIterator);
    }

    // Flatten a list into a single string
    private static String flattenStringList(List<String> list) {
        StringBuilder ret = new StringBuilder();
        for (String s : list) {
            ret.append(s);
        }
        return ret.toString();
    }


    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return listIterator.hasNext();
            }

            @Override
            public String next() {
                return flattenStringList(listIterator.next());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
