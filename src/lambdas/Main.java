package lambdas;


public class Main {

    public static void main(String[] args) {


        System.out.println(
                getBetterElement("Greedo", "Han", (x, y) -> x.compareTo(y) > 0)
        );

        System.out.println(
                getBetterElement(20, 10, (x, y) -> x.compareTo(y) > 0)
        );


    }

    public static <T> T getBetterElement(T e1, T e2, BiPredicate<T> pred) {

        boolean b = pred.test(e1, e2);

        if (b == false) {
            return e2;
        } else {
            return e1;
        }


    }
}
