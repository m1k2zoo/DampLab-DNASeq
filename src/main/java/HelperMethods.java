import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class HelperMethods {

    private static final int LENGTH = 219;

    public static void main(String[] args) throws IOException {
        getClassification(179993);
    }

    public static void getClassification(int TaxID) throws IOException {
//        String[] group = new String[LENGTH];
        int i = 0;
        int identified = 0;

        CSVReader reader = new CSVReader(new FileReader("TaxIDAustraliaGroup.csv"));
        String[] line;
        reader.readNext();
        i++;
        while ((line = reader.readNext()) != null) {
            if (Integer.parseInt(line[0]) == TaxID) {
                printInfo(line);
                identified++;
            }
//            group[i-1] = line[8];
            i++;
        }
        if(identified == 0) {
          System.out.println("Sequence with ID number" + TaxID + " is not in IGSC databases. Very safe. " +
                  "If it's the highest hit, synthesize!");
        }
    }

    private static void printInfo(String[] identifiedOrganism) {
        System.out.println("Organism: " + identifiedOrganism[1]);
        System.out.println("Group: " + identifiedOrganism[2]);
        System.out.println("Pathogen Name: " + identifiedOrganism[6]);
        System.out.println("IGSC Risk Level: " + identifiedOrganism[8]);
        if(identifiedOrganism[8].equals("IV"))
            System.out.println("Instructions: Please manually confirm " +
                    "that the sequence is not related" +
                    " to a toxin or to the pathogenicity of an organism." +
                    "\nThen you can go ahead and produce the sequence.");
    }
}
