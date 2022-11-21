package project;


//Felix Lidö feli8145

import java.io.InputStream;
import java.util.Scanner;

public class Reader {

    private static Scanner input;
    private final InputStream inStream;

    public Reader(InputStream inStream) {

        this.inStream = inStream;
    }

    public Reader(){
        this.inStream = System.in;
    }

    public int readInt(String text){
        print(text);
        input = new Scanner(inStream);
        return Integer.parseInt(input.nextLine());
    }

    public String readString(String text){
        print(text);
        input = new Scanner(inStream);
        return input.nextLine();
    }

    public double readDouble(String text){
        print(text);
        input = new Scanner(inStream);
        return Double.parseDouble(input.nextLine().replace(",", "."));
    }

    private void print(String text){
        System.out.print(text + "?>");
    }
}

