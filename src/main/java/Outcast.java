public class Outcast {
    private final WordNet wordnet;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }
    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxLength = 0;
        String oustcastNoun = null;
        for (String x : nouns) {
            int currentLength = 0;
            for (String y : nouns) {
                if (!x.equals(y)) {
                    currentLength += wordnet.distance(x, y);
                }
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    oustcastNoun = x;
                }
            }
        }
        return oustcastNoun;
    }
    // see test client below
    public static void main(String[] args) {
    }
}
