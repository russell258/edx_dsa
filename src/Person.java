public class Person implements Comparable<Person> {
    private String name;
    private int age;

    // constructor, getters, setters

    @Override
    public int compareTo(Person otherPerson) {
        return otherPerson.getAge() - age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}