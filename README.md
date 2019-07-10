# DampLab-DNASeq
#### Applies screening nethods to DNA sequences to ensure safe gene synthesis.
The program starts with a FASTA file, BLASTs it against the database of choice, and then contrast the hits of the BLAST search 
against our databases to determine whether the sequence is harmful or not. 
Note that you can choose which database to run the BLAST search 
against by manipulating **String[] command**, which is initialized at the beginning **src/main/java/Main.java**

### Prerequisites
In order to run this program, you will need to have a subset of the NCBI BLAST databases downloaded on your machine. 
To learn more about using the BLAST tool (and NCBI databases) on your local machine, 
navigate through [this link](https://blast.ncbi.nlm.nih.gov/Blast.cgi?PAGE_TYPE=BlastDocs&DOC_TYPE=Download).
