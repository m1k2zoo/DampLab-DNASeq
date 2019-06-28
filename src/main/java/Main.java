import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // This command runs a BLAST search on the local host
        String[] command =
                {
                        "blastn",
                        "-query",
                        // the following path should be modified to reflect
                        // the correct FASTA filepath
                        "/home/kumail/blastdb/test_query.fa",
                        "-db",
                        // the following should be modified to reflect where
                        // the BLAST databases are stored
                        "/home/kumail/blastdb/refseq_rna.00",
                        "-task",
                        "blastn",
                        "-dust",
                        "no",
                        "-outfmt",
                        // the following specifies that we're looking for Tax IDs
                        "7 staxids",
                        "-max_target_seqs",
                        "2",
                };

        // Create a process to run the shell command and save the output to out.txt
        ProcessBuilder builder;
        builder = new ProcessBuilder(command);
        builder.redirectOutput(new File("out.txt"));
        builder.redirectError(new File("out.txt"));
        Process p; // may throw IOException
        try {
            p = builder.start();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a list of the TaxIDs returned from the BLAST search
        ArrayList<Integer> IDs = HelperMethods.extractIDs("out.txt");

        // getClassification compares the TaxIDs against the IGSC databases
        // 179993 is an example ID of a dangerous toxin
        System.out.println("Hit number 1:");
        HelperMethods.getClassification(179993);
        System.out.println();
        for (int i = 0; i < IDs.size(); i++) {
            System.out.println("Hit number " + (i+2) + ":");
            HelperMethods.getClassification(IDs.get(i));
            System.out.println();
        }

    }

}
