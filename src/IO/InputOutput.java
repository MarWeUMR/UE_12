package IO;

import java.io.*;

public class InputOutput {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\Marcus\\IdeaProjects\\UE_12\\oop.dat.sec");

        DataInputStream dis = null;

        FileInputStream fis = null;

        try {

            fis = new FileInputStream(file);

            dis = new DataInputStream(fis);



            while (dis.available() > 0) {
                char k = dis.readChar();
                System.out.print(k + " ");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }

        }


    }

}
