package hw_5.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.random.RandomGenerator;

public class Application {
    public static void main(String[] args) {
        ListOperations listOfOperations = new ListOperations();

        int[] arrayOfNums = {109, 23, 67, 12, 3, 7, 4};
        List<Integer> listOfNumbers = List.of(57, 12, 47, 89,
                25, 67, 12, 39, 74, 50, 33, 98, 24, 67, 88, 19, 73, 46, 29, 57);
        List<String> listOfWords = List.of("book", "cat", "dog", "egg",
                "fish", "goat", "hat", "moon", "egg", "kite", "lamp",
                "moon", "nest", "owl", "cat"
        );

//        int occurred = list.countOccurrence(listOfWords,"book");
//        System.out.println(occurred);

//        List<Integer> convertToList = list.toList(arrayOfNums);
//            System.out.println(convertToList);

//        Set<Integer> unique = list.findUnique(listOfNumbers);
//        System.out.println(listOfNumbers);
//        System.out.println(unique);

        listOfOperations.findOccurrence(listOfWords);

    }
}
