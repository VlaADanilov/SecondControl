package org.example;

import java.io.*;
import java.util.Arrays;

public class ReaderAndWriterToLog extends Thread {
    private int k; // размер в байтах строки с данными в формате UTF-8
    private String s = ""; // строка с данными
    private int d; // контрольное число - количество символов в строке с данными
    private int p; // номер части
    private File file;
    private File fileToWrite;

    private boolean flag;

    public ReaderAndWriterToLog(File fileToRead, File fileToWrite){
        file = fileToRead;
        this.fileToWrite = fileToWrite;
    }


    public void run(){
        readFromFile();
        if (s.length() != d){
            System.out.println("Файл"+ file.getName() +  "некорректен! Я не буду записывать его данные");
            return;
        }
        writeToFile();
    }

    //2. Прочитать содержимое файлов, проверить соответствие количества
    // символов с контрольным числом; записать в лог-файл: "прочитали файл
    // [имя файла] кол-во байт данных: ..., кол-во считанных символов:...,
    // контрольное число:...., номер части:....".

    public String getS() {
        return s;
    }

    public int getP() {
        return p;
    }

    private synchronized void writeToFile(){
        try(FileWriter writer = new FileWriter(fileToWrite, true)){
            writer.write(getInfo() + "\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readFromFile(){
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
            k = dis.readInt();
            byte[] arr = new byte[k];
            for (int i = 0; i < k; i += 1){
                arr[i] = (byte)dis.read();
            }
            d = dis.readInt();
            p = dis.readInt();
            try(Reader reader = new InputStreamReader(new ByteArrayInputStream(arr))) {
                int s = 0;
                while ((s = reader.read()) != -1){
                    this.s += (char) s;
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getInfo(){
        return "прочитали файл "
                + file.getName() + " кол-во байт данных : " + k +
                " кол-во считанных символов: " +  s.length() +
                " контрольное число: " + d +
                " номер части: " + p;
    }
}
