import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class KmerCountTable {

    int capacity;
    int numberOfElements;
    ArrayList<LinkedList<KmerCount>> table;

    // Constructs an initially empty hash table with capacity of 7
    public KmerCountTable() {
        // Declare needed arrays and linked lists and initialize
        // variables as appropriate

        // Initially the table is holding no data elements
        numberOfElements = 0;

        // Initially, the capacity is 7.
        // That means there are seven spots in the array holding linked lists
        // and thus 7 possible indices that hash values can get mapped to
        capacity = 7;

        // Make the array be an ArrayList holding up to capacity linked lists
        table = new ArrayList<LinkedList<KmerCount>>(capacity);
        // Allocate each of the linked lists
        for (int index = 0; index < capacity; index = index + 1) {
            table.add(new LinkedList<KmerCount>());
        }
    }

    // Returns the size of the hash table in terms of how many items have
    // been stored in the table
    public int size() {
        // Return how many elements have been added to the table
        return numberOfElements;
    }

    // Returns the load factor (size / capacity) for the hash table
    private double getLoadFactor() {
        // Compute the load factor as the ratio of the number of elements
        // held in the table over the capacity value
        return (1.0 * numberOfElements)/capacity;
    }

    public void increment(String keyKmerArg) {
        // take in a String representing a 3-mer
        KmerCount inKmer;

        boolean kmernotinlist= true;
        //compute a hash-value for the input 3-mer
        // then map that hash-value to an index in the table
        int Kindex = (keyKmerArg.hashCode())%capacity;
        //At that index, there is a linked list.
        LinkedList<KmerCount> List=table.get(Kindex);
        ListIterator<KmerCount> iterator =List.listIterator(0);
        KmerCount kmerCountObject;
        if (List.isEmpty()) {
            //create a KmerCount object in the linkedlist if the LinkedList is empty
            inKmer = new KmerCount(keyKmerArg);
            List.addLast(inKmer);
            numberOfElements+=1;
        }
        else {
            while (iterator.hasNext()) {
                kmerCountObject = iterator.next();
                if (kmerCountObject.getKmer().equals(keyKmerArg)){
                    //If a KmerCount object holding the 3-mer passed in exists in the linked list at this index
                    //use the incrementCount method of the found KmerCount object to
                    //update the count for this 3-mer
                    kmerCountObject.incrementCount();
                    kmernotinlist = false;
                    break;
                }
            }
            if(kmernotinlist) {
                //If a KmerCount object holding the 3-mer passed in does not exist in the linked list
                //Create a new KmerCount object to the end of that linked list
                inKmer = new KmerCount(keyKmerArg);
                List.addLast(inKmer);
                numberOfElements+=1;
            }
        }

        //if the load factor is greater than 0.75
        //resize the hashtable and rehash it
        //the resize method is defined below
        if(getLoadFactor()>0.75){
            resize();
        }

    }

    public Integer retrieve(String keyKmerArg) {
        //This method found KmerCount object to retrieve and return the count for that 3-mer
        //Using the Java .hashCode() method available for strings
        //compute a hash-value for the input 3-mer
        //and then map that hash-value to an index in the table.
        int Kindex = (keyKmerArg.hashCode())%capacity;
        int kcount = 0;
        //At that index, there is a linked list
        LinkedList<KmerCount> List= table.get(Kindex);
        ListIterator<KmerCount> iterator =List.listIterator(0);
        KmerCount kmerCountObject;
        boolean kmernotinlist=true;
        while (iterator.hasNext()) {
            kmerCountObject = iterator.next();
            //If a KmerCount object holding the 3-mer passed in exists in the linked list at this index
            //use the getCount method of the found KmerCount object to retrieve and return the count for that 3-mer
            if (kmerCountObject.getKmer().equals(keyKmerArg)) {
                kmernotinlist = false;
                kcount=kmerCountObject.getCount();
                break;
            }
        }

        if(kmernotinlist) {
            //if the object related to 3-mer does not exist in the linked list, return null
            return null;
        }
        else{

            return kcount;
        }
    }


    private void resize() {
        //Create a new hash table that has a new capacity equal to 2 ∗ c + 1
        //where c is the current capacity
        ArrayList<LinkedList<KmerCount>> newtable;
        int newcapacity = capacity*2+1;
        LinkedList<KmerCount> newlist;
        LinkedList<KmerCount> llistkmer;
        KmerCount kmerCountObject;
        newtable = new ArrayList<LinkedList<KmerCount>>(newcapacity);
        for (int index = 0; index < newcapacity; index = index + 1) {
            newtable.add(new LinkedList<KmerCount>());
        }
        ListIterator<LinkedList<KmerCount>> iteratorT  =   table.listIterator(0);
        //each item in the current hashtable is rehashed given the new capacity and stored in the new
        //larger ArrayList of linked lists
        while(iteratorT.hasNext()){
            llistkmer = iteratorT.next();
            ListIterator<KmerCount> iteratorL = llistkmer.listIterator(0);
            while(iteratorL.hasNext()){
                kmerCountObject = iteratorL.next();
                int newKindex = (kmerCountObject.getKmer().hashCode())%newcapacity;
                newlist = newtable.get(newKindex);
                newlist.addLast(kmerCountObject);
            }
        }

        //reset the newtable as the current table
        //reset the current capacity to the newcapacity
        table = newtable;
        System.out.println("resize from capacity "+capacity+" to new capacity "+newcapacity);
        capacity = newcapacity;
    }

    // Prints descriptive information about and the contents of the hash table
    public void print() {
        // This method does the following:
        // Print some information about the hashtable
        System.out.println("==> Hashtable Dump <== ");
        System.out.println("Number of elements: " + numberOfElements);
        System.out.println("Capacity: " + capacity);
        System.out.println("Load Factor: " + getLoadFactor());

        // Declare variables that will be useful in visiting
        // all data in the hashtable
        LinkedList<KmerCount> list;
        KmerCount kmerCount;

        // For each linked list (at each index in the table)
        for (int index = 0; index < capacity; index = index + 1) {
            System.out.print("" + index + ":");

            // Get the linked list at that index
            list = table.get(index);

            // If the linked list is empty, print as such
            if (list.isEmpty()) {
                System.out.println(" --> empty <-- ");
            }
            else {
                // Otherwise, iterate down the linked list,
                // starting at position 0 and going until
                // there is no more data, printing the data
                // held for each kmer and count in the table
                System.out.println(" --> list has " + list.size() + " entries <-- ");
                ListIterator<KmerCount> iterator = list.listIterator(0);
                while (iterator.hasNext()) {
                    kmerCount = iterator.next();
                    System.out.println("\t" + kmerCount.toString());
                }
            }
        }
    }
}
