package de.umr.oop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class IOStreams {

    public static Map<String, Integer> countWords(Stream<String> words) {

        Map<String, Integer> map = new HashMap<String, Integer>();

        String[] sRay = words.toArray(String[]::new);
        String[] splitRay = sRay[0].split(" ");


        for (String s : splitRay) {
            s = s.toLowerCase();
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                int count = map.get(s);
                map.put(s, count + 1);
            }
        }

        map.forEach((String x, Integer y) -> {
            System.out.println(String.format(
                    "%s: %d", x, y
            ));
        });

        return map;


    }


    public static Stream<String> encrypt(Stream<String> words, int offset) {

        //words.forEach(String::toUpperCase);

        List<String> stringList = words
                .collect(toList());

        stringList.replaceAll(String::toUpperCase);

        stringList.replaceAll(IOStreams::replaceUmlaut);


        stringList.replaceAll(x -> {

            StringBuilder sb = new StringBuilder();

            char[] cRay = x.toCharArray();

            for (int i = 0; i < cRay.length; i++) {
                if (cRay[i] == 32) { // space
                    sb.append(cRay[i]);
                } else { //encrypt
                    if (cRay[i] + offset > 90) {
                        sb.append((char) ((cRay[i] + offset) - 26));
                    } else {
                        sb.append((char) (cRay[i] + offset));
                    }
                }
            }

            return sb.toString();

        });

        stringList.forEach(System.out::println);
        return stringList.stream();

    }


    public static Stream<String> words(String filePath) {

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> stringList = new ArrayList<>();

        try {
            String line = bufferedReader.readLine();

            while (line != null) {

                char[] cRay = line.toCharArray();

                StringBuilder sb = new StringBuilder();

                // filter all non word chars out
                for (char c :
                        cRay) {
                    if (c == 32 || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                        sb.append(c);
                    }
                }
                stringList.add(sb.toString());

                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream<String> str = stringList.stream();

        return str;
    }


    private static String replaceUmlaut(String input) {

        //replace all capital umlaute
        return input.replace("Ü", "UE")
                .replace("Ö", "OE")
                .replace("Ä", "AE")
                .replace("ß", "SS");
    }

}
