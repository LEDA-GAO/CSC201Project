# CSC201Project
Project practiced in the CSC 201 course

## Project 2 
The codes' files of the project 2 are in the folder Project 2. \
Seperate chaining hashing is used to implement a biology problem. \
Given a string, there is an associated set of k-mer(especially 3-mers) which represent each consecutive sequence of 3-symbols from the string.\
In my understanding, this string can be a DNA sequence. \
These codes can help us manipulate the statistic of 3-mers in a string dynamically by using hash table and linked list.\
There are severl files listed below. 

### KmerCount.java 
It is a class used to define the k-mer objects. Take a 3-mer as example. \
3-mer is represented by a string with 3 characters, such as ART. \
Variable count are used to count the number of a specific k-mer appearing in a sequance of string(DNA chain)

### KmerCountTable.java 
It is the class to implement the hashtable. There are 5 main methods in this class. 
#### increment
It takes in a String representing a 3-mer. Using the Java .hashCode() method available for strings, compute a hash-value
for the input 3-mer, and then map that hash-value to an index in the table. At that index, there is a linked
list. The linked list contains KmerCount objects representing 3-mers which have previously been stored in
the table. If a KmerCount object holding the 3-mer passed in exists in the linked list at this index, use the
incrementCount method of the found KmerCount object to update the count for this 3-mer. If a KmerCount
object holding the 3-mer passed in does not exist in the linked list at this index, construct a new KmerCount
object and store it at the end of the linked list. Finally, if the use of increment causes the load factor to go
above 0.75, call the resize method for the hashtable. 

#### retrieve 
It takes in a String representing a 3-mer. Using the
Java .hashCode() method available for strings, compute a hash-value for
the input 3-mer, and then map that hash-value to an index in the table. At that index, there is a linked list.
If a KmerCount object holding the 3-mer passed in exists in the linked list at this index, use the getCount
method of the found KmerCount object to retrieve and return the count for that 3-mer. If a KmerCount
object holding the 3-mer passed in does not exist in the linked list at this index, return null. 

#### resize 
It should create a new ArrayList holding linked lists, with a new capacity equal to 2 ∗ c + 1 where c represents the current capacity.
Then, each item in the current hashtable should be rehashed given the new capacity and stored in the new
larger ArrayList of linked lists. After this is done for each item, the new ArrayList should be set as the
table underlying the hashtable.

#### getLoadFactor
It returns the load factor (size / capacity) for the hash table. 

#### print 
It prints descriptive information about and the contents of the hash table. 

### prx1.txt & prx2.txt 
These txt files give the sequence of string that are used to be counted the number of k-mers. 

### query.txt 
The query txt file consists of different 3-mers. You can use it to test the retrieve method for the hash table. 

### main.java 
It is the code to test the implementation of hash table by using all the files above. 










