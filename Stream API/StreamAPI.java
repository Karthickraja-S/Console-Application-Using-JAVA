import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class Employee {
    public Employee(int departmentID, String name,int salary) {
        this.departmentID = departmentID;
        this.name = name;
        this.salary = salary;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getName() {
        return name;
    }
    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "departmentID=" + departmentID +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                "}\n";
    }

    private int departmentID;
    private String name;
    private int salary;
}
public class StreamAPI {
    /**
     * Short Summary & Key takeaways :
     * <p>
     * stream() has methods like
     * <br>
     * forEach() where u can do each values inside the  Consumer Interface ( accept void method )
     * <br>
     * filter() which accepts a predicate impl and returns a boolean value. The data which returns true will be filtered
     * <br>
     * map() which u can accept each value in stream and u can return any values u want by modifing with ur logic
     * <br>
     * sorted() which sorts the data with internal logic. You can even override the sorting logic with comparator
     * <br>
     * max() , min() which u can find max,min values by internal sorting / customised sorting based on comparator overriding
     * <br>
     * skip() which accepts the num and skips the position to that place , limit() accepts num and give only the range u given.
     * <br>
     * anymatch() - accepts a predicate impl and if any value returns true, then there is a data already in stream of list
     * <br>
     * collect() - accepts a collector where u can join, convert to List , groupBy data with
     * Collectors.GroupingBy() , Collectors.toMap() etc.,
     * <br>
     * Even we can combine 2 stream with union() method of Stream Class and we can use Stream methods like the above.
     */
    public static void main(String[] args) {
        // Write a program to find the sum of all elements in a list using Java Stream API
        List<Integer> nums = Arrays.asList(10,12,33,4,15,12,0,22,0,1,12,22,22,1,1,33);
        final int[] sum = {nums.stream().mapToInt(value -> value).sum()};
        System.out.println("Addition of all elements : "+ sum[0]);

        // Given a list of integers, write a program to find and print the maximum element using Java Stream API
        System.out.println("Min element : "+ nums.stream().mapToInt(value -> value).min().getAsInt());
        System.out.println("Max element : "+ nums.stream().mapToInt(value -> value).max().getAsInt());

        System.out.println("Max element of first 5 num: "+ nums.stream().mapToInt(value -> value)
                .limit(5).max().getAsInt());

        // Write a program to filter out all the even numbers from a list using Java Stream API
        List<Integer> evenNum = nums.stream().filter(x -> x%2==0).collect(Collectors.toList());
        System.out.println("Even numbers : "+evenNum);

        //4. Given a list of strings, write a program to count the number of strings containing a
        // specific character ‘a’ using Java Stream API.
        List<String> words = Arrays.asList("Cat" , "Ball" , "Apple" , "Donkey" , "Hoodie", "Goat" , "Fan" , "Elephant");
        long wordsWithACount = words.stream().filter(w -> w.toLowerCase().contains("a")).count();
        System.out.println("Words count which has a  : "+wordsWithACount);

        // Write a program to convert a list of strings to uppercase using Java Stream API.
        List<String> wordsToUpperCase = words.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Words to Upper Case : "+wordsToUpperCase);

        // Given a list of integers, write a program to calculate the average of all the numbers using Java Stream API.
        OptionalDouble average = nums.stream().mapToInt(x->x).average();
        if(average.isPresent()) {
            System.out.println("Average : "+average.getAsDouble());
        }

        // Write a program to sort a list of strings in alphabetical order using Java Stream API.
        Collections.sort(words); // SIMILAR : words.stream().sorted().collect(Collectors.toList());
        System.out.println("Words sorted : "+words);

        // Given a list of strings, write a program to concatenate all the strings using Java Stream API.
        String joinedWord = words.stream().collect(Collectors.joining()); // We can also use String.join( "<DELIMITER_IF>" , words)
        System.out.println("Words joined : "+joinedWord);

        // Write a program to find the longest string in a list of strings using Java Stream API.
        Optional<String> longword = words.stream().max((o1, o2) -> o1.length() > o2.length() ? 1 : -1);
        System.out.println("Long word : "+ (longword.orElse("Not Available , seems empty list")));

        // Given a list of integers, write a program to find and print the second largest number using Java Stream API.
        System.out.println("Second largest no : "+nums.stream().sorted().skip(1).findFirst().orElse(-1));

        final int[] pdt = {1};
        nums.forEach(integer -> pdt[0] *= integer);
        System.out.println("Pdt of all element : "+pdt[0]);

        // Write a program to find the intersection of two lists of strings using Java Stream API.
        List<Integer> list1 = Arrays.asList(1,2,5,6,7,8,9,0);
        List<Integer> list2 = Arrays.asList(1,0,122);
        List<Integer> list1OnList2 = list1.stream().filter(list1Val -> list2.contains(list1Val)).collect(Collectors.toList());
        // we can even reduce to .filter(list2::contains)
        System.out.println("List1 Intersection List2 : "+list1OnList2);

        // Write a program to find the union of two lists of integers using Java Stream API.
        List<Integer> list1AndList2 = Stream.concat(list1.stream() , list2.stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("List1 Union List2 : "+list1AndList2);

        // Write a program to find the mode of a list ( number which occurrs more time ) of integers using Java Stream API.
        // List<Integer> nums = Arrays.asList(10,12,33,4,15,12,0,22,0,1,12,22,22,1,1,33);

        Map<Integer,Long> frequencyMap = nums.stream()
                .collect(Collectors.groupingBy(integer -> integer , Collectors.counting()));
        System.out.println("Frequency Mapping : "+frequencyMap);

        long maxFreq = frequencyMap.values().stream().mapToLong(v -> v).max().orElse(0L);

        List<Integer> keyWithMaxFreq = frequencyMap.keySet().stream()
                .filter(x-> frequencyMap.get(x) == maxFreq)
                .collect(Collectors.toList());
        System.out.println("Nums that occur maximum : "+keyWithMaxFreq);

        //Given a list of strings, write a program to find and
        // print the strings with the maximum number of vowels using Java Stream API.

        Map<String , Long> LambdaExp = words.stream()
                .collect(Collectors.toMap(s -> s,
                        s -> s.chars().filter(ch -> "AEIOUaeiou".indexOf(ch) != -1).count()));

        Map<String , Long> freqWordsAndVowelCount = words.stream().collect(Collectors.toMap(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        }, new Function<String, Long>() {
            @Override
            public Long apply(String s) {
               return s.chars().filter( ch -> "AEIOUaeiou".indexOf(ch) != -1).count();
            }
        }));

        System.out.println(LambdaExp);
        System.out.println(freqWordsAndVowelCount);
        // now we can find which word has maximum vowel

        long maxVowelCount = freqWordsAndVowelCount.values().stream().mapToLong(x -> x).max().orElse(0L);

        List<String> wordsThatHasMaxFreq = freqWordsAndVowelCount.keySet().stream()
                .filter(x-> freqWordsAndVowelCount.get(x)==maxVowelCount)
                .collect(Collectors.toList());
        System.out.println("Word that is maximum vowels : "+wordsThatHasMaxFreq);

        // -------------- PLAYING WITH CUSTOM OBJECTS -------------------
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Karthi",60000));
        employees.add(new Employee(1,"Sham",60000));

        employees.add(new Employee(2,"Vicky",90000));
        employees.add(new Employee(2,"Saran",160000));
        employees.add(new Employee(2,"Dhanvi",80000));

        employees.add(new Employee(3,"Sri",50000));
        employees.add(new Employee(3,"Sundar",70000));

        employees.add(new Employee(4,"Guna",60000));

        // in above example ( getting freq of a number ) , they used key as number
        // value as the Collectors.counting(), if not used the value , then they will hav return type as
        // Map<Integer , list<Integer> >

        // so if u need empCount based on department , then use Collectors.counting() as a second argument to
        // groupingBy method
        Map<Integer , List<Employee>> empGrpByDeptID = employees
                .stream().
                collect(Collectors.groupingBy(employee -> employee.getDepartmentID()));

        Map<Integer , List<String>> empNameGrpByDeptID = employees
                .stream().
                collect(Collectors.groupingBy(employee -> employee.getDepartmentID() ,
                        Collectors.mapping(employee -> employee.getName()  , Collectors.toList())));


        Map<Integer, List<Employee>> highestSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        emp -> emp.getDepartmentID(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                empList -> {
                                    int maxSalary = empList.stream().mapToInt(emp -> emp.getSalary())
                                            .max().orElse(0);
                                    return empList.stream()
                                            .filter(emp -> emp.getSalary() == maxSalary)
                                            .collect(Collectors.toList());
                                }
                        )
                ));


        System.out.println("Employee Obj group by DepartmentID : "+empGrpByDeptID);
        System.out.println("Employee Name group by DepartmentID : "+empNameGrpByDeptID);
        System.out.println("Highest Salary Getting Employee group by DepartmentID : "+highestSalaryByDepartment);
    }
}
