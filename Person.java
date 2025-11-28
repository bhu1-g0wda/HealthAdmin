package healthadmin;

//Sealed Interface demonstrating inheritance and polymorphism
sealed interface Person permits Patient, Staff {
 String getName();
 int getAge();
}

