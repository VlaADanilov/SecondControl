package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

public class Main {
    public static final String VARIANT = "v11";
    public static void main(String[] args) {
        File f = new File(VARIANT);
        File[] files = f.listFiles();

        PaintPartThread[] parts = new PaintPartThread[files.length];
        int index = 0;
        for (File file : files){
            parts[index] = new PaintPartThread(file);
            index++;
        }

        for (PaintPartThread p : parts){
            p.start();
        }

        for (PaintPartThread p : parts){
            try {
                p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        File rezult = new File(VARIANT+".png");
        Arrays.sort(parts, (o1, o2) -> o1.getPart() - o2.getPart());
        try(FileOutputStream fos = new FileOutputStream(rezult)){
            for (PaintPartThread p : parts){
                fos.write(p.getData());
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        for (PaintPartThread p : parts){
            if (p.check()) System.out.println("Верно");
            else{
                System.out.println("Не верно");
            }
        }

    }
}
