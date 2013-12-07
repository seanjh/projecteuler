/*
* http://projecteuler.net/problem=1
* Multiples of 3 and 5
* Problem 1
* If we list all the natural numbers below 10 that are multiples of 3 or 5, we 
* get 3, 5, 6 and 9. The sum of these multiples is 23.
*
* Find the sum of all the multiples of 3 or 5 below 1000.
* 
*/

public class PE1 {
    public static void main(String[] args) {
        final int LIMIT = 1000;
        int sum = 0;
        
        for (int i = 1; i < LIMIT; i++) {
            if ((i % 3 == 0) || (i % 5 == 0))
                sum += i;
        }

        System.out.printf("Sum of the multiples of %d & %d less than %d: ", 
            3, 5, LIMIT);
        System.out.println(sum);
    }
}