public class KmerCount {

    private String kmer;
    private Integer count;

    // Create a new KmerCount object, given a kmer string
    // Associate a count of 1 with the object (it has been seen once)
    public KmerCount(String kmerArg) {
        kmer = kmerArg;
        count = 1;
    }

    // Return the kmer string
    public String getKmer() {
        return kmer;
    }

    // Increment the count of how many times the kmer has been seen
    public void incrementCount() {
        count = count + 1;
    }

    // Return how many times the kmer has been seen
    public Integer getCount() {
        return count;
    }

    // Return a string that includes both the kmer and the count value, e.g. (ART, 7)
    public String toString() {
        return "(" + kmer + "," + count.toString() + ")";
    }
}
