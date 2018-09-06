package liv.rx;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class ParallelRx {
    public static void main(String args[]) {
        List<String> inputs = new ArrayList<>();
        for (String s : args) {
            inputs.add(s);
        }

        new ParallelRx().parallelize(inputs);
    }

    public String parallelize(List<String> lst) {
        return Observable.from(lst)
                .flatMap(s ->
                        Observable.just(s)
                                .map(this::lower)
                                .doOnError(System.err::println)
                                .map(str -> "STRING " + str)
                                .subscribeOn(Schedulers.computation())
                )
                .reduce("", (prev, another) -> prev + another)
                .toBlocking()
                .single();
    }

    public String lower(String l) {
        return l.toLowerCase();
    }
}
