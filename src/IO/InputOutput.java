package IO;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class InputOutput {

    public static void main(String[] args) {
        // String rep = Integer.toBinaryString((i & 0xFF) + 256).substring(1);

        // create file instance
        File file = new File("C:\\Users\\Marcus\\IdeaProjects\\UE_12\\oop.dat.sec");

        // reserve dis variable
        DataInputStream dis = null;

        try {
            // instantiate dis with new filestream
            dis = new DataInputStream(new FileInputStream(file));

            // string for char sequence
            String temp = "";

            // byte representation of the file content
            byte[] buf = new byte[dis.available()];
            dis.read(buf);

            // loop over byte array
            for (int currentPosition = 0; currentPosition < buf.length - 1; currentPosition++) {

                // create char from bytes
                char c = byteToChar(buf[currentPosition], buf[++currentPosition]);
                temp += c;

                // create int from bytes
                int i = byteToInt(buf[++currentPosition], buf[++currentPosition], buf[++currentPosition], buf[++currentPosition]);
                currentPosition += i;

                // check if message done
                if (i < 0) {
                    System.out.println(temp);
                    break;
                }
            }
            // terminate stream
            dis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream ps = null;
        DataOutputStream dos = null;

        File psFile = new File("PrintStream.bin");
        File dosFile = new File("DataOutputStream.bin");

        try {
            ps = new PrintStream(psFile);
            dos = new DataOutputStream(new FileOutputStream(dosFile));

            for (short i = 10000; i < 32000; i++) {
                ps.print(i);
                dos.writeShort(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        File readDosFile = new File("DataOutputStream.bin");
        File readPsFile = new File("PrintStream.bin");

        System.out.println("----------------------------------------------------------");
        System.out.println("Aufgabe 12.2 b)");
        System.out.println(String.format("Filesize DataOutputStream: %d Bytes", readDosFile.length()));
        System.out.println(String.format("Filesize PrintStream: %d Bytes", readPsFile.length()));
        System.out.println("PrintStream schreibt die Daten menschlich lesbar in kodierter Form in die Datei. Hierzu muss mehr Information gespeichert werden, als es für bloße 2-byte Speicherung von DataOutputStream nötig ist.");

    }

    private static char byteToChar(int a, int b) {

        // convert byte to 16 bits and shift first byte to left half of 16 bits
        return (char) ((a << 8) & 0xFF00 | (b) & 0x00FF);
    }

    private static int byteToInt(int a, int b, int c, int d) {

        // convert byte to 32 bits and shift first byte to left most part
        return (a << 24) & 0xFF000000 | (b << 16) & 0x00FF0000 | (c << 8) & 0x0000FF00 | d & 0x000000FF;
    }

}
