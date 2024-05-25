package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;

public class MainNot {

    public static final String VARIANT = "v19";

    public static void main(String[] args) {
        File file = new File(VARIANT);
        File rezult = new File(VARIANT + ".log");
        File[] f = file.listFiles();
        ReaderAndWriterToLog[] threads = new ReaderAndWriterToLog[f.length];
        int index = 0;

        //Очистка log файла, чтобы не дублировалась информация в случае, если пользователь
        //дважды вызвал запуск программы
        clearLog();

        for (File f1 : f){
            ReaderAndWriterToLog r = new ReaderAndWriterToLog(f1, rezult);
            threads[index] = r;
            r.start();
            index++;
        }


        for (Thread t : threads){
            try{
                t.join();
            } catch (Exception e){
                e.printStackTrace();
            }
        }


        Arrays.sort(threads, Comparator.comparingInt(ReaderAndWriterToLog::getP));


        // Допилить с уже как-то заполненным файлом
        File text = new File(VARIANT + ".txt");
        for (ReaderAndWriterToLog r : threads){
            System.out.println(r.getP());
            try(Writer writer = (r.getP() != 0) ? new FileWriter(text, true) : new FileWriter(text)){
                writer.write(r.getS() + " ");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void clearLog(){
        File file = new File(VARIANT + ".log");
        try {
            Writer writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
