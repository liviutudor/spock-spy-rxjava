package liv.rx


import spock.lang.Specification

class ParallelRxTest extends Specification {
    def "parallel"() {
        List<String> lst = ["ABC", "XYz", "one"]
        def x = Spy(ParallelRx)

        when:
        def r = x.parallelize(lst)

        then:
        r == "STRING xyzSTRING abcSTRING one"
//        1 * x.lower("ABC") >> "abc"
//        1 * x.lower("XYz") >> "xyz"
//        1 * x.lower("one") >> "one"
    }
}
