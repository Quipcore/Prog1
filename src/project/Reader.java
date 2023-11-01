package project;//Felix Lidö feli8145

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {


    private static List<InputStream> currentInStreams = new ArrayList<>();
    private final Scanner input;


    public Reader(InputStream inStream) throws IllegalStateException {
        if(currentInStreams.contains(inStream)){
            throw new IllegalStateException("Instance of class already exists");
        }

        currentInStreams.add(inStream);
        input = new Scanner(inStream);
    }

    public Reader(){
        this(System.in);
    }

    public int readInt(String prompt){
        print(prompt);
        return Integer.parseInt(input.nextLine());
    }

    public String readString(String text){
        print(text);
        String retString = "";
        if(input.hasNextLine()){
            retString  = input.nextLine();
        }
        return retString;
    }

    public double readDouble(String text){
        print(text);
        return Double.parseDouble(input.nextLine().replace(",", "."));
    }

    private void print(String text){
        System.out.print(text + "?>");
    }
}