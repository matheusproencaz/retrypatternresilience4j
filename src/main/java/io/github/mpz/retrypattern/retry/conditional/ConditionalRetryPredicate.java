package io.github.mpz.retrypattern.retry.conditional;

import java.util.function.Predicate;

public class ConditionalRetryPredicate implements Predicate<String> {

    @Override
    public boolean test(String str) {
        return !str.equals("teste 5");
    }
}
