public class Person extends Animal
{
    private String name; private int account;
    public int getAccount() { return account; }
    public Person(String name, int account, int legs)
    {
        super(legs);
        this.name = name;
        this.account = account;
    }
    public String getName() { return name; }
    public String toString() { return super.toString() + " and a Person object whose name is " + name; }
    public String greet(Person p) { return "Hi." ; }
    public String greet(Animal a) { return "Grrr" ; }
    public boolean equals(Object o) { return super.equals(o) && name.equals(((Person)o).name); }
    public void getPaid(int amount) { account += amount; }
}