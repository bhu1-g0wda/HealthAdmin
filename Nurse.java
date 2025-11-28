package healthadmin;

// 1. Changed 'implements Staff' to 'extends Employee'
final class Nurse extends Employee {
    // 'id' and 'name' are removed, now inherited from Employee
    private String ward;

    public Nurse(String name, String ward) {
        // 1. super() constructor call to the Employee class
        super(name, "N");
        this.ward = ward;
    }

    // getId() and getName() are removed, now inherited from Employee

    @Override
    public int getAge() { return 28; }

    @Override
    public String getDepartment() { return ward; }

    // 1. Demonstrating super.method() call
    @Override
    public void greet() {
        super.greet(); // Calls the Employee.greet() base method
        System.out.println("I work in the " + ward + " ward.");
    }

    @Override
    public String toString() {
        // Uses inherited getId() and getName()
        return getId() + ": Nurse " + getName() + " (" + ward + ")";
    }
}