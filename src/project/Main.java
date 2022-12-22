package project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {

    String packagePath = Main.class.getPackageName().replace(".", "/");


    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        Random rnd = new Random();

        Stream<String> stream = Files.lines(getPathof("homescreen"));

        stream.forEach(System.out::println);




        input.close();
    }


    private static Path getPathof(String str){
        String packagePath = Main.class.getPackageName().replace(".", "/");
        return Path.of("src/"+packagePath +"/" + str);
    }
}
