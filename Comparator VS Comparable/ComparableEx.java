import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparable<Student> {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "} \n ";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo( Student otherStudent) {
        // Sorts with age
        // if age is equal , then short name will come first.
        if( this.age > otherStudent.age) {
            return 1;
        } else {
            if ( this.age == otherStudent.age ) {
                if ( this.name.length() > otherStudent.name.length() ) return 1;
            }
        }
        return -1;
    }
}

public class ComparableEx {
    public static void main(String[] args) {
        // when we use comparable, we will restrict sorting the class by our logic itself.
        // if they wanna modify the sorting logic, then they can use comparator to override our logic.
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Karthick",23));
        studentList.add(new Student("Gopi",20));
        studentList.add(new Student("Sreeni",17));
        studentList.add(new Student("Surya",56));
        studentList.add(new Student("Tester",7));
        studentList.add(new Student("Go",20));
        studentList.add(new Student("Dhanvi",1));
        studentList.add(new Student("GopiExtended",20));

        Collections.sort(studentList);
        System.out.println("Default sorting : "+studentList);

        // now i dont need the default sorting , i need custom sort. How to achieve?
        // Yes , you have to write own comparator logic

        Comparator<Student> comparator = ( o1, o2) -> {
                if(o1.getName().length() > o2.getName().length()) {
                    return 1;
                }
                return -1;
            };
        Collections.sort(studentList , comparator);
        System.out.println("Own custom sorting : "+studentList);



    }
}
