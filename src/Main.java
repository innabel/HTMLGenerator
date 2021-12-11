import java.io.*;
import java.util.*;

public class Main {

    // colors-related variables
    final static String ANSI_RESET = "\u001B[0m";
    final static String ANSI_YELLOW = "\u001B[33m";


    public static void main(String[] args) {

        /** Main features of the program:

        1. ask a user for a file name
        2. check if this file already exists
        3. change the extension of the file from .txt to .html
        4. check if this file is empty
        5. read each line from the file and add necessary tags **/


        Scanner scanner = new Scanner(System.in);
        Scanner fileIn = null; // input file connection
        PrintWriter fileOut = null; // HTML file connection
        String fileNameIn; // what the user types in - original file's name
        String fileNameOut; // new HTML file's name
        int dotIndex; // position of any dots within the filename - for renaming purposes
        String line = null; // for reading purposes, from the input file



        // 1. ask a user for a file name (or file path)

        System.out.println(ANSI_YELLOW + "Enter file name or path" + ANSI_RESET);
        String s = scanner.nextLine();
        fileNameIn = s;


        // 2. check if this file already exists
        // 3. change the extension of the file from .txt to .html

        try {
            fileIn = new Scanner(new FileReader(fileNameIn));
            // getting the index of the dot
            dotIndex = fileNameIn.lastIndexOf(".");
            if(dotIndex == -1) {
                fileNameOut = fileNameIn + ".html";
            } else {
                fileNameOut = fileNameIn.substring(0, dotIndex) + ".html";
            }
            fileOut = new PrintWriter(fileNameOut);
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }

        // 4. check if this file is empty

        try {
            line = fileIn.nextLine();
        }
        catch(NoSuchElementException e) {
            System.out.println(ANSI_YELLOW + "Error: " + e.getMessage() + ANSI_RESET);
        }
        if(line == null) {
            System.out.println(ANSI_YELLOW + "This file is empty" + ANSI_RESET);
        } else {
            // 5. read each line from the file and add necessary tags
            fileOut.println("<html>");
            fileOut.println("<head>");
            fileOut.println("</head>");
            fileOut.println("<body>");
            fileOut.println(line);

            while(fileIn.hasNextLine()) {
                fileOut.println("<br>");
                line = fileIn.nextLine();

                if(line.isEmpty()) {
                    fileOut.println("<br>");
                } else {
                    fileOut.println(line);
                }
            }

            fileOut.println("</body>");
            fileOut.println("</html>");

            System.out.println(ANSI_YELLOW + "HTML file is processed." + ANSI_RESET);
        }

        fileIn.close();
        fileOut.close();


    } // end of main method
} // end of Main class
