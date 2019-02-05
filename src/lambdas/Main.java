package lambdas;


public class Main {

    public static void main(String[] args) {

        System.out.println(
                getBetterElement("Greedo", "Han", (x, y) -> x.compareTo(y) > 0)
        );

        System.out.println(
                getBetterElement(2, 2, (x, y) -> x.compareTo(y) > 0)
        );


    }

    public static <T> T getBetterElement(T e1, T e2, BiPredicate<T> pred) {

        if (e1 == null || e2 == null) {
            throw new NullPointerException();
        }

        return pred.test(e1, e2) ? e1 : e2;
    }
}
