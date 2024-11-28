package ClassScheduling;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {

    public CsvWriter(){

    }

    public static void writeFile(String name,String dir,List<String> lines){

        File output = new File("data/"+dir+"/"+name);
        makeDir(output);
        try(PrintWriter pw = new PrintWriter(output)){

            for (String line: lines) {
                pw.println(line);
            }
        }catch (FileNotFoundException e){}
    }

    public static void writeFile(String name,String dir,String lines){

        File output = new File("data/"+dir+"/"+name);
        makeDir(output);
        try(PrintWriter pw = new PrintWriter(output)){
            pw.println(lines);
        }catch (FileNotFoundException e){}
    }


    private static void makeDir(File f){
        // Ensure directories exist
        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Create directories if they do not exist
        }
    }



    public static void combineFilesFromDirectory(String directoryPath, String filename) {

        File directory = new File(directoryPath+"/"+filename+"/");
        List<List<String>> allData = new ArrayList<>();
        List<String> combinedData = new ArrayList<>();
        int maxRows = 500;
        String outputFilePath = directoryPath+"-combined";

        // Get all CSV files in the directory
        File[] csvFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
        if (csvFiles == null || csvFiles.length == 0) {
            System.out.println("No CSV files found in the directory: " + directoryPath);
            return;
        }

        // Read each CSV file
        for (File file : csvFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                List<String> fileData = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    fileData.add(line);
                }
                allData.add(fileData);
                System.out.println("Read file: " + file.getName());
            } catch (IOException e) {
                System.err.println("Error reading file: " + file.getAbsolutePath());
            }
        }

        // Combine data column-wise
        for (int i = 0; i < maxRows; i++) {
            StringBuilder row = new StringBuilder();
            for (List<String> fileData : allData) {
                if (i < fileData.size()) {
                    row.append(fileData.get(i)).append(","); // Add row data
                } else {
                    row.append(",,,"); // Add empty row data if the file has fewer rows
                }
            }
            // Remove the trailing comma
            if (row.length() > 0 && row.charAt(row.length() - 1) == ',') {
                row.setLength(row.length() - 1);
            }
            combinedData.add(row.toString());
        }

        // Write the combined data to the output file
        writeFile(filename+".csv",outputFilePath,combinedData);
    }


    public static void main(String[] args) {
        combineFilesFromDirectory(args[0],args[1]);
    }


}
