package entities;

public class Address {

    private String city;
    private String state;
    private String street;
    private String zip;

    public Address(String city, String state, String street, String zip) {
        this.city = city;
        this.state = state;
        this.street = street;
        this.zip = zip;
    }
 @Override
    public String toString() {
        return "City: " + getCity()+ " " +
                getState() + " " + getStreet()+ " " + getZip();
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}