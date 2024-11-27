package ClassScheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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


}
