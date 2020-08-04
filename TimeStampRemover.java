// This program will remove the timestamps from automated transcripts of zoom recordings.
// Any blank lines will also be removed unless a small change is made in the code.
// Depending on the format, timestamps may not be successfully removed.

import java.util.*;
import java.io.*;

public class TimeStampRemover {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      intro();
      readFile(console);
   }
   
   public static void intro() {
      System.out.println("Welcome to the Timestamp Remover!");
      System.out.println("This program removes timestamps from the zoom automated transcripts.");
      System.out.println("I could've just removed them manually... but where's the fun in that?");
      System.out.println("If it doesn't work, try saving the transcript as a .txt file :)");
      System.out.println();
   }
   
   // This asks for user input to check if the input file exists,
   // and scans the input, to create the output file.
   // Parameters:
   //    Scanner console - to prompt the user for the file names.
   public static void readFile(Scanner console) throws FileNotFoundException {
      System.out.print("Input file name: ");
      String inputName = console.nextLine();
      
      File inputFile = new File(inputName);
      while (!inputFile.exists()){
         System.out.print("File does not exist. Try again: ");
         inputName = console.nextLine();
         inputFile = new File(inputName);
      }  
      
      System.out.print("Output file name: ");
      String outputName = console.nextLine();
      File outputFile = new File(outputName);
      PrintStream printToFile = new PrintStream(outputFile);
      
      Scanner fileScan = new Scanner(inputFile); // scanner for the entire file input
      while (fileScan.hasNextLine()){
         removeTime(fileScan, printToFile); // scanner for a single line of the input
      }
      
      System.out.println("Timestamps successfully removed!");
      System.out.println();
   }
   
   // This method removes the timestamps from the transcript
   // and only allows the dialogue to be printed in the output file.
   // Assumes the dialogue does not contain digits or brackets
   // (timestamp formats typically start with a digit or a bracket)
   // Assumes the lines do not start with blank space(s) before the dialogue or timestamp
   // Parameters:
   //    Scanner fileLine - to get each line in the input file
   //    PrintStream toFile - prints text to the output file
   public static void removeTime(Scanner fileLine, PrintStream toFile) {
      String line = fileLine.nextLine();
      line = line + " ";
      char first = line.charAt(0);
      if (!Character.isDigit(first) && !line.startsWith("[") && line.length() != 1) {
      // delete the [line.length() != 1] to keep the blank lines
         toFile.println(line);
      }
   }
}
