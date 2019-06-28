import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelperMethods {

    private static final int LENGTH = 219; // Number of lines in the CSV file

    public static void main(String[] args) throws IOException {
        int example = 179993;
        getClassification(example);
    }


    /* This function extracts the Tax IDs resulting from the output of BLAST
It extracts the IDs for the top 3 hits, and puts them in an ArrayList
The code should probably be rewritten to choose the IDs based on
some similarity metric*/
    public static ArrayList<Integer> extractIDs(String textFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(textFile));
        String s = "string";
        while (!s.equals("found")) { // The IDs appear after the word found in the output of BLAST
            s = scanner.next();
        }
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        int n = 0;
        while (scanner.hasNextInt() && n < 3) {
            IDs.add(scanner.nextInt());
            n++;
        }

        scanner.close();

        return IDs;
    }


    /* This function gets a Tax ID, and runs it against the IGSC database
It classifies the corresponding sequence into one of 5 risk levels
It also runs another function that prints out the classification and subsequent instructions */
    public static void getClassification(int TaxID) throws IOException {
        int identified = 0;
        CSVReader reader = new CSVReader(new FileReader("TaxIDAustraliaGroup.csv"));
        String[] line;
        reader.readNext();
        while ((line = reader.readNext()) != null) {
            // if TaxID is found in the IGSC database, classify it and print instructions
            if (Integer.parseInt(line[0]) == TaxID) {
                printInfo(line);
                identified++;
            }
        }
        // if TaxID is not in the database, print the following
        if (identified == 0) {
            System.out.println("Sequence with ID number" + TaxID + " is not in IGSC databases. Very safe. " +
                    "If it's the highest hit, synthesize!");
        }
    }


    /* This function prints information about the sequence
It instructs the administrator on whether to proceed with the synthesis
It is still not complete. More code should be added to handle all cases*/
    private static void printInfo(String[] identifiedOrganism) {
        System.out.println("Organism: " + identifiedOrganism[1]);
        System.out.println("Group: " + identifiedOrganism[2]); // the indices come from the structure of CSV
        System.out.println("Pathogen Name: " + identifiedOrganism[6]);
        System.out.println("IGSC Risk Level: " + identifiedOrganism[8]);

        // The following is one example concerning the case when the risk level = 4
        // Similar code should be added to handle the other four cases
        if (identifiedOrganism[8].equals("IV"))
            System.out.println("Instructions: Please manually confirm " +
                    "that the sequence is not related" +
                    " to a toxin or to the pathogenicity of an organism." +
                    "\nThen you can go ahead and produce the sequence.");
    }

}