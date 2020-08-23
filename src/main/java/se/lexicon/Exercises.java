package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Person;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;



public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        Predicate<Person> firstNameErik = person -> person.getFirstName().equalsIgnoreCase("Erik");

        //storage.findMany();
        List<Person> personList = storage.findMany(firstNameErik);
        //forEach
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
                   storage.findMany(person -> person.getGender() == Gender.FEMALE)
                    .forEach(System.out::println);

           // System.out.println("----------------------");
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        LocalDate date = LocalDate.parse("2000-01-01");
        Predicate<Person> bornAfter = person ->
                person.getBirthDate().isAfter(date) || person.getBirthDate().equals(date);

        storage.findMany(bornAfter).forEach(System.out::println);

        System.out.println("----------------------");
    } //Write your code here
    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        System.out.println(storage.findOne(person->person.getId()==123));
        System.out.println("----------------------");
    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        System.out.println(storage.findOneAndMapToString(person->person.getId()==123,
                                                         person -> "Name: "+person.getFirstName()+" "+person.getLastName()+" born: "+person.getBirthDate()));
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println(storage.findManyAndMapEachToString( person -> person.getGender() == Gender.MALE && person.getFirstName().startsWith("E"),
                person -> "Name: "+person.getFirstName()+" "+person.getLastName()+" born: "+person.getBirthDate()
        ));;
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message){
        System.out.println(message);
        //Write your code here

        LocalDate date = LocalDate.parse("2010-01-01");
        System.out.println(storage.findManyAndMapEachToString(
                person -> person.getBirthDate().isAfter(date),
                person -> "Name: "+person.getFirstName()+" "+person.getLastName()+" born: "+person.getBirthDate()
        ));

        System.out.println("----------------------");
    }


    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> firstNameUlf = person -> person.getFirstName().equalsIgnoreCase("Ulf");
        List<Person> personList = storage.findAndDo(firstNameUlf);
        //forEach
        personList.forEach(System.out::println);
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message){
        System.out.println(message);
        //Write your code here

        storage.findAndDo(
                person -> person.getLastName().contains(person.getFirstName()),
                person -> System.out.println(person)
        );

        System.out.println("----------------------");
    }
    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here

                storage.findAndDo(
                        person -> person.getFirstName().equalsIgnoreCase(new StringBuilder(person.getFirstName()).reverse().toString()),
                        person -> System.out.println(person)
                );

    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here

         storage.findAndSort(person->person.getFirstName().startsWith("A"),
                 Comparator.comparing(Person::getBirthDate));
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        LocalDate date = LocalDate.parse("1950-01-01");

        System.out.println(storage.findAndSort(
                person -> person.getBirthDate().isBefore(date),
                Comparator.comparing(Person::getBirthDate).reversed()
        ));
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }
}
