package ClassScheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public CsvWriter(){

    }

    public static void writeFile(String name,List<String> lines){

        File output = new File("data/"+name);
        try(PrintWriter pw = new PrintWriter(output)){

            for (String line: lines) {
                pw.println(line);
            }
        }catch (FileNotFoundException e){}
    }

    public static void writeFile(String name,String lines){

        File output = new File("data/"+name);
        try(PrintWriter pw = new PrintWriter(output)){
            pw.println(lines);
        }catch (FileNotFoundException e){}
    }



}
