package de.umr.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class IOStreamsTest {

    @Test
    public void words() {

        String[] expected = new String[]{"Wait a minute Doc Ah Are you telling me you built a time machine out of a DeLorean",
                "These arent the droids youre looking for",
                "Extraordinary claims require extraordinary evidence"};

        Stream<String> s = IOStreams.words("quotes.txt");

        String[] actual = s.toArray(String[]::new);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encrypt() {

        String expected = "THIS IS AEH TEST";

        Stream<String> crypt = Stream.of("This is Äh Test");

        Stream<String> encrypted = IOStreams.encrypt(crypt, 13);

        Stream<String> encryptedBack = IOStreams.encrypt(encrypted, 13);

        String actual = encryptedBack
                .collect(Collectors.joining(" "));

        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countWords() {

        String pale = "From this distant vantage point, the Earth might not seem of any particular interest. But for us, it's different. Consider again that dot. That's here, that's home, that's us. On it everyone you love, everyone you know, everyone you ever heard of, every human being who ever was, lived out their lives. The aggregate of our joy and suffering, thousands of confident religions, ideologies, and economic doctrines, every hunter and forager, every hero and coward, every creator and destroyer of civilization, every king and peasant, every young couple in love, every mother and father, hopeful child, inventor and explorer, every teacher of morals, every corrupt politician, every superstar, every supreme leader, every saint and sinner in the history of our species lived there. On the mote of dust suspended in a sunbeam.";
        pale = pale.replace(".", "");
        pale = pale.replace(",", "");


        IOStreams.countWords(Stream.of(pale));

    }
}