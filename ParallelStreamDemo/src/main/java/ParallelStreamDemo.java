import java.util.stream.LongStream;

public class ParallelStreamDemo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long result = LongStream
                .range(1_000_000, 1_000_000_000)
                //.parallel() // не имеет смысла использовать на очень простых вычислениях, т.к. время на распаралеливание увеличит общее время вычисления
                .filter(n -> n % 3 == 0)
                .reduce((a, b) -> (long) (Math.log(a) + Math.log(b)))
                .getAsLong();

        long stop  = System.currentTimeMillis();
        System.out.println("result: " + result);
        System.out.println("elapsed time: " + (stop - start) + " ms");
    }
}
