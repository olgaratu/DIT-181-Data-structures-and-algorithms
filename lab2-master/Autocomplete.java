import java.util.Arrays;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocomplete(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N) where N is the number of dictionary terms
    private void sortDictionary() {
        // TODO

        Arrays.sort(dictionary, Term.byLexicographicOrder);

        // for (Term a : dictionary
        // ) {
        // System.out.println(a);
        // }
        // throw new UnsupportedOperationException();
    }

    // Returns all terms that start with the given prefix, in descending order of
    // weight.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N + M log M) where M is the number of matching terms
    public Term[] allMatches(String prefix) {
        // TODO
        if (prefix == null) {
            throw new java.lang.NullPointerException();
        }
        int first = RangeBinarySearch.firstIndexOf(dictionary, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));
        if (first == -1)
            return new Term[0];
        int last = RangeBinarySearch.lastIndexOf(dictionary, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if (last == -1)
            return new Term[0];
        Term[] result = new Term[last - first + 1];
        int index = 0;
        for (int i = first; i <= last; i++) {
            result[index++] = dictionary[i];
        }
        Arrays.sort(result, Term.byReverseWeightOrder);

        return result;

        // throw new UnsupportedOperationException();
    }

    // Returns the number of terms that start with the given prefix.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N) where N is the number of dictionary terms
    public int numberOfMatches(String prefix) {
        // TODO
        if (prefix == null) {
            throw new java.lang.NullPointerException();
        }
        int first = RangeBinarySearch.firstIndexOf(dictionary, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));
        if (first == -1)
            return 0;
        int last = RangeBinarySearch.lastIndexOf(dictionary, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        return last - first + 1;

        // throw new UnsupportedOperationException();
    }

}
