import java.io.File;
import java.util.Scanner;

public class Main { // Or whatever name you specify in your README [cite: 109]
    public static void main(String[] args) {

        String inputFilepath, outputFilepath; 
        inputFilepath = args[0]; // get the input file path from the command line 
        outputFilepath = args[1]; // get the output file path 
        
        File inputFile = new File(inputFilepath); 
        File outputFile = new File(outputFilepath);

        // Create a Scanner object for the input file
        Scanner scanner = new Scanner(inputFile);

        LazyBinarySearchTree tree = new LazyBinarySearchTree();

        while(scanner.hasNext()){
            String line = scanner.nextLine(); 
            if (line.startsWith("Insert"))
                tree.insert(Integer.parseInt(line.substring(7))); // get the number after "Insert " and insert it into the tree
            else if (line.startsWith("Delete"))
                tree.delete(Integer.parseInt(line.substring(7))); // get the number after "Delete " and delete it from the tree
            else if (line.startsWith("Contains"))
                tree.contains(Integer.parseInt(line.substring(9))); // get the number after "Contains " and check if it's in the tree
        }

        scanner.close();


        
        


    }
}
