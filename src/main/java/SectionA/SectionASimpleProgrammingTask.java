package SectionA;

public class SectionASimpleProgrammingTask {

    /* Section A – Simple Programming Task
    Describe an algorithm which sums all numbers from 1 to 1000000 both numbers
    inclusive in the simplest possible way.

    Solution:
    To solve would be used an arithmetic progression formula for sum  of arithmetic sequence:
    n(a1 + an)/2 = 1000000(1+1000000)/2= 500000500000
    it would be more quick and simple then using loop and quicker then using recursion.
    Below is example with Java.
    */


    public static void main(String[] args) {
        long a, d, n, sum;
        a = 1;
        n = 1000000;
        d = 2;
        sum = n*(n+a)/d;
        System.out.println(sum);
    }
}