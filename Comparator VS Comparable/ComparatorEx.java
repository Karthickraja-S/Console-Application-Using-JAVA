import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComparatorEx {
    public static void main(String[] args) {
        // comparable is used when u need to specify sort logic by yourself and
        // not depending on default sorting logic.

        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(123);
        nums.add(12);
        nums.add(8);
        nums.add(8);
        nums.add(1);
        nums.add(1);
        nums.add(20);
        nums.add(100);

        Collections.sort(nums);
        System.out.println("Sorted By Default Method :: ");
        System.out.println(nums);

        System.out.println("Going to sort with custom Logic where im going to sort with the last number");
        Comparator<Integer> customSortComparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //  (o1%10) > (o2%10)  ? 1 : -1
                return Integer.compare(o1 % 10, o2 % 10);
            }
        };

        // we can also use the below lambda expressions.
         Comparator<Integer> customSortLambdaComparator = Comparator.comparingInt(o -> o % 10);

        nums.sort(customSortLambdaComparator);
        System.out.println(nums);

        // Hmm, what if i want #20 Collections.sort(nums); code itself need to have my own sorting.
        // There Comparable Interface comes into picture, when the class implements those class , the sorting logic
        // will be coupled to that class

    }
}
