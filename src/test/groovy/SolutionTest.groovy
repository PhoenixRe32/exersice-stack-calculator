import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by andreas.andreou on 19/11/2016.
 */
class SolutionTest extends Specification {

    Solution solution = new Solution()

    @Unroll
    def "Solution"() {
        when:
        def result = solution.solution(cmdLine)

        then:
        assert result == expectedResult

        where:
        cmdLine                            | expectedResult
        "13 DUP 4 POP 5 DUP + DUP + -"     | 7

        "13 DUP 4 POP 5 DUP + DUP + - DUP" | -1

        "A DUP - -"                        | -1
        "-1 DUP +"                         | -1
        "1048575 DUP +"                    | -1

        "6 -"                              | -1
        "6 5 -"                            | -1

        "5 6 + +"                          | -1
        "1048570 DUP +"                    | -1

        "POP 5 -"                          | -1
        "DUP 5 -"                          | -1
    }
}
