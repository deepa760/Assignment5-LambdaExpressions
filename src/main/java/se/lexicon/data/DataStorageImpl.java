package se.lexicon.data;

import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl<comparator> implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;
    private Object Person;

    private DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        for (Person person : personList) {
            boolean isFilter = filter.test(person);
            if (isFilter) {
                return person;
            }
        }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {

        Person p = findOne(filter);
        return personToString.apply(p);
    }


    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString)
    {
        List<String> stringList = new ArrayList<>();
        for(Person person : personList){
            if(filter.test(person)){
                stringList.add(person.toString());
            }
        }
        return stringList;
    }
    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {
        for(Person person : personList) {
            if (filter.test(person)) {
                consumer.accept(person);
            }
        }

    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator)
    {
        List<Person> result = new ArrayList<>();
        for (Person person : personList)
        {
            result.add(person);
        }
        result.sort(comparator);
        return result;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator)
    {
        List<Person>result=new ArrayList();
        for(Person person : personList){
            if(filter.test(person)){
                result.add(person);
            }
        }
        result.sort(comparator);
        return result;
    }
}