package DB;

import Entity.Regex_Sample;

import java.io.*;
import java.util.ArrayList;

public class Database_Action {
    public static String File_URL = "Data/Regex_Sample.txt";

    public static ArrayList<Regex_Sample> readFile(){
        BufferedReader br = null;
        ArrayList<Regex_Sample> regexList = new ArrayList<Regex_Sample>();
        try {
            if (new File(File_URL).exists()){
                br = new BufferedReader(new FileReader(File_URL));
                if (br.ready()){
                    String line = br.readLine();
                    if (!line.trim().equals("") && line != null){
                        String[] cutted = line.split(";");
                        Regex_Sample r = new Regex_Sample(cutted[0], cutted[1], cutted[2]);
                        regexList.add(r);
                    }
                }
                br.close();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return regexList;
    }

    public static void writeToFile(ArrayList<Regex_Sample> regexList){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(File_URL));
            for (Regex_Sample r : regexList)
                bw.write(r.toString() + "\n");
            bw.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
