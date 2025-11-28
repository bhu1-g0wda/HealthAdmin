package healthadmin;

// 5. Updated sealed clause to permit the new abstract base class
sealed interface Staff extends Person permits Employee {
    String getDepartment();
    String getId(); // Added ID to interface for cleaner polymorphism

    // static interface method
    static void hospitalPolicy() {
        System.out.println("All staff must adhere to patient confidentiality.");
    }

    // default interface method
    default void greet() {
        System.out.println("Hello, I'm " + getName() + " from " + getDepartment());
    }

    // 4. New default method to demonstrate private method usage
    default String getFullTitle() {
        // Calls the private method
        return getTitlePrefix() + " " + getName();
    }

    // 4. Private interface method
    private String getTitlePrefix() {
        if (this instanceof Doctor d) {
            return "Dr.";
        } else if (this instanceof Nurse n) {
            return "Nurse";
        }
        return "Staff";
    }
}