package x5.mantis.common;

import x5.mantis.tests.TestBase;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Common extends TestBase {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNubers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNubers).limit(n).map(i -> 'a' + i).map(Character::toString).collect(Collectors.joining());
        return result;
    }
}