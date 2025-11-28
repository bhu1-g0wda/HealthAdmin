package healthadmin;

import java.util.UUID;

// 1. Abstract class for super() & super.method()
// 5. This class is part of the sealed Staff hierarchy
public abstract non-sealed class Employee implements Staff {
    protected final String id;
    protected String name;

    public Employee(String name, String idPrefix) {
        // super() from Object is implicit
        this.name = name;
        this.id = idPrefix + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    // 1. This is the base method subclasses will call via super.greet()
    @Override
    public void greet() {
        System.out.println("--- Staff Greeting ---");
        // 4. Using the default method from the interface
        System.out.println("Hello, I'm " + getFullTitle());
    }

    // getAge() and getDepartment() remain abstract,
    // forcing Doctor and Nurse to implement them.
}