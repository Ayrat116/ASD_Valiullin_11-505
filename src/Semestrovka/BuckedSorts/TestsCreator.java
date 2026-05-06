package Semestrovka.BuckedSorts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestsCreator {
    public static void main(String[] args) throws IOException {
        Random r = new Random();
        FileWriter f = new FileWriter("test_data.txt");
        for (int i = 0; i < 50; i++) {               // 50 наборов
            int cnt = r.nextInt(100, 10001);         // размер от 100 до 10000
            for (int j = 0; j < cnt; j++) {
                f.write(String.valueOf(r.nextInt(1, 10001))); // число от 1 до 10000
                if (j < cnt - 1) f.write(" ");
            }
            f.write("\n");
        }
        f.close();
        System.out.println("Файл test_data.txt создан");
    }
}