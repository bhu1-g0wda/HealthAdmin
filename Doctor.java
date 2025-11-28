package healthadmin;

// 1. Changed 'implements Staff' to 'extends Employee'
final class Doctor extends Employee {
    // 'id' and 'name' are removed, now inherited from Employee
    private String specialty;

    public Doctor(String name, String specialty) {
        // 1. super() constructor call to the Employee class
        super(name, "D");
        this.specialty = specialty;
    }

    // getId() and getName() are removed, now inherited from Employee

    @Override
    public int getAge() { return 35; }

    @Override
    public String getDepartment() { return specialty; }

    // 1. Demonstrating super.method() call
    @Override
    public void greet() {
        super.greet(); // Calls the Employee.greet() base method
        System.out.println("My specialty is " + specialty);
    }

    @Override
    public String toString() {
        // Uses inherited getId() and getName()
        return getId() + ": Dr. " + getName() + " [" + specialty + "]";
    }
}