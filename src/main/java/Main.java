import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] command =
                {
                        "blastn",
                        "-query",
                        "/home/kumail/blastdb/test_query.fa",
                        "-db",
                        "/home/kumail/blastdb/refseq_rna.00",
                        "-task",
                        "blastn",
                        "-dust",
                        "no",
                        "-outfmt",
                        "7 staxids",
                        "-max_target_seqs",
                        "2",
                };
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

        ArrayList<Integer> IDs = extractIDs("out.txt");

        System.out.println("Hit number 1:");
        HelperMethods.getClassification(179993);
        System.out.println();
        for (int i = 0; i < IDs.size(); i++) {
            System.out.println("Hit number " + (i+2) + ":");
            HelperMethods.getClassification(IDs.get(i));
            System.out.println();
        }

    }

    private static ArrayList<Integer> extractIDs(String textFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(textFile));
        String s = "string";
        while (!s.equals("found")) {
            s = scanner.next();
        }
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        int n = 0;
        while(scanner.hasNextInt() && n < 3)
        {
            IDs.add(scanner.nextInt());
            n++;
        }

        scanner.close();

        return IDs;
    }
}
