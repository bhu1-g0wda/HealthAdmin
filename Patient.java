package healthadmin;

//Demonstrates encapsulation, constructor chaining, and defensive copying
final class Patient implements Person 
{
    private String name;
    private int age;
    private final Contact contact;
    private final ImmutableAddress address;  // NEW
    
    public Patient(String name, int age) {
        // calls another constructor in same class
        this(name, age, new Contact("unknown@mail.com", "0000000000"), new ImmutableAddress("NA", "NA"));
    }

public Patient(String name, int age, Contact contact, ImmutableAddress address) 
{
        this.name = name;
        this.age = age;
        this.contact = new Contact(contact); // defensive copy
        this.address = address;               // immutable, no defensive copy needed
}

@Override
public String toString() {
        return name + " (Age: " + age + ", " + contact + ", " + address + ")";
    }

 @Override
 public String getName() { return name; }

 @Override
 public int getAge() { return age; }

 public Contact getContact() { return new Contact(contact); }

 }


