package healthadmin;

//Immutable custom type
public final class ImmutableAddress {
 private final String street;
 private final String city;

 public ImmutableAddress(String street, String city) {
     this.street = street;
     this.city = city;
 }

 public String street() { return street; }
 public String city() { return city; }

 @Override
 public String toString() {
     return street + ", " + city;
 }
}

