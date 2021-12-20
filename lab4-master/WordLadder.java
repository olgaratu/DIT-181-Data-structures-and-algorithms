
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import java.util.stream.Collectors;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A graph that encodes word ladders.
 *
 * The class does not store the full graph in memory, just a dictionary of
 * words. The edges are then computed on demand.
 */
public class WordLadder implements DirectedGraph<String> {

    private Set<String> dictionary;
    private Set<Character> charset;

    /**
     * Creates a new empty graph.
     */
    public WordLadder() {
        dictionary = new HashSet<>();
        charset = new HashSet<>();
    }

    /**
     * Creates a new word ladder graph from the given dictionary file. The file
     * should contain one word per line, except lines starting with "#".
     * 
     * @param file path to a text file
     */
    public WordLadder(String file) throws IOException {
        dictionary = new HashSet<>();
        charset = new HashSet<>();
        Files.lines(Paths.get(file)).filter(line -> !line.startsWith("#")).forEach(word -> addWord(word.trim()));
    }

    /**
     * Adds the {@code word} to the dictionary, if it only contains letters. The
     * word is converted to lowercase.
     * 
     * @param word the word
     */
    public void addWord(String word) {
        if (word.matches("\\p{L}+")) {
            word = word.toLowerCase();
            dictionary.add(word);
            for (char c : word.toCharArray())
                charset.add(c);
        }
    }

    /**
     * @return the number of words in the dictionary
     */
    public int nNodes() {
        return dictionary.size();
    }

    /**
     * @param w a graph node (a word)
     * @return a list of the graph edges that originate from {@code w}
     */
    public List<DirectedEdge<String>> outgoingEdges(String w) {
        /******************************
         * TODO: Task 2 * Change below this comment *
         ******************************/
        LinkedList<DirectedEdge<String>> outgoingEdge = new LinkedList<>();
        HashSet<String> newWordSet = new HashSet<>();
        for (int i = 0; i < w.length(); i++) {
            for (Character charc : charset) {
                StringBuilder nextWord = new StringBuilder(w);
                nextWord.setCharAt(i, charc);
                if (dictionary.contains(nextWord.toString()) && !newWordSet.contains(nextWord.toString())
                        && !w.equals(nextWord.toString())) {
                    newWordSet.add(nextWord.toString());
                    DirectedEdge<String> edge = new DirectedEdge<String>(w, nextWord.toString());
                    outgoingEdge.add(edge);// duplication maybe?
                }
            }
        }

        return outgoingEdge;
    }

    /**
     * @param w one node/word
     * @param u another node/word
     * @return the guessed best cost for getting from {@code w} to {@code u} (the
     *         number of differing character positions)
     */
    public double guessCost(String w, String u) {
        /******************************
         * TODO: Task 4 * Change below this comment *
         ******************************/
        double estimateCost = 0;

        if (w.length() != u.length()) {
            estimateCost = Double.POSITIVE_INFINITY;
            return estimateCost;
        }

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) != u.charAt(i)) {
                estimateCost++;
            }
        }
        return estimateCost;
    }





    /**
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        StringWriter buffer = new StringWriter();
        PrintWriter w = new PrintWriter(buffer);
        w.println("Word ladder with " + nNodes() + " words, charset: \""
                + charset.stream().map(Object::toString).collect(Collectors.joining()) + "\"");
        w.println();

        int ctr = 0;
        w.println("Example words and ladder steps:");
        for (String v : dictionary) {
            if (v.length() != 5)
                continue;
            List<DirectedEdge<String>> edges = outgoingEdges(v);
            if (edges.isEmpty())
                continue;
            w.println(v + " --> " + edges.stream().map(e -> e.to()).collect(Collectors.joining(", ")));
            if (ctr++ >= 10)
                break;
        }
        return buffer.toString();
    }

    /**
     * Unit tests the class
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println(new WordLadder(args[0]));
        } catch (Exception e) {
            // If there is an error, print it and a little command-line help
            e.printStackTrace();
            System.err.println();
            System.err.println("Usage: java WordLadder dictionary-file");
            System.exit(1);
        }
    }

}
