package md2html;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestKvadrat {
    public static void main(String[] args) {
        String s = "[ссылок с _выд*ел**еa--sa__sd`as\\*das`da__sd--as**ни*ем_](https://kgeorgiy.info)";
        int kol = 1_00_0_000_000 / s.length();
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/input.txt")))) {
            for(int i = 0; i<kol; i++){
                out.println(s);
                out.println();
            }
        } catch (IOException e) {

        }
        System.out.println("1 part done");
        Md2Html.main(null);
    }
}
