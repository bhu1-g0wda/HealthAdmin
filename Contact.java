package healthadmin;

//Demonstrates call-by-value and defensive copying
final class Contact {
 private String email;
 private String phone;

 public Contact(String email, String phone) {
     this.email = email;
     this.phone = phone;
 }

 public Contact(Contact other) {
     this(other.email, other.phone);
 }

 @Override
 public String toString() {
     return email + " | " + phone;
 }
}

