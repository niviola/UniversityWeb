package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class University {

    private String building;    
    private String staff;
    private int testscore = 10;
    private String name = "Stanford";
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Map<UUID, Student> listofstudents = new HashMap<>();

    public Map<UUID, Student> getListofstudents() {
        return listofstudents;
    }

    public void setListofstudents(Map<UUID, Student> listofstudents) {
        this.listofstudents = listofstudents;
    }
    
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public int getTestscore() {
        return testscore;
    }

    public void setTestscore(int testscore) {
        this.testscore = testscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
