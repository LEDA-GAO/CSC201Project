import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // Use 3-mers (ART, KLM, NGT)
        int kmerLength = 3;

        // Variables useful for processing the sequence data
        String kmer;
        Integer count;
        String sequence = null;
        int sequenceLength = -1;
        int stoppingPoint = -1;

        // A counter for using with for loops
        int index;

        // Create the hash table
        KmerCountTable hashTable = new KmerCountTable();

        // Open the prx2. txt file and read each line
        // Break each line into 3-mers
        // For each 3-mer, increment its count by updating the hashtable
        Scanner scanner = new Scanner(new File("src/prx2. txt"));


        while (scanner.hasNextLine()) {
            sequence = scanner.nextLine();
            sequenceLength = sequence.length();
            stoppingPoint = sequenceLength - kmerLength + 1;

            for (index = 0; index < stoppingPoint; index = index + 1) {
                kmer = sequence.substring(index,index+kmerLength);
                System.out.println(kmer + " " + kmer.hashCode());
                hashTable.increment(kmer);
            }
        }
        scanner.close();

        // Print descriptive information about and the contents of the hash table
        hashTable.print();

        // Open the query. txt file and reach each line holding a 3-mer
        // For each 3-mer, retrieve it (and its count) from the hashtable
        // and report whether, and if appropriate, how often it occurred
        // in the sequence data
        scanner = new Scanner(new File("src/query. txt"));
        while (scanner.hasNextLine()) {
            kmer = scanner.nextLine();
            count = hashTable.retrieve(kmer);
            if (count == null) {
                System.out.println("Queried kmer " + kmer + " not in sequence data.");
            }
            else {
                System.out.println("Queried kmer " + kmer + " appeared " + count + " times in sequence data.");
            }
        }
        scanner.close();
    }
}
