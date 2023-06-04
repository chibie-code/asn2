package ca.sfu.cmpt276.asn2.models;

        import jakarta.persistence.*;
        import org.springframework.web.bind.annotation.RequestBody;



@Entity
// this class models the db table called "Student"
@Table(name = "Student")
public class Student {

    // declare properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String name;
    private double weight;
    private double height;
    private String hairColor;
    private double gpa;

    // default constructor
    public Student() {}

    // non-default constructor
    public Student(
            String name,
            double weight,
            double height,
            String hairColor,
            double gpa
    ){
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        this.gpa = gpa;
    }

    public String getName() {
        return this.name;
    }

    public void setName(@RequestBody String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(@RequestBody double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(@RequestBody double height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(@RequestBody String hairColor) {
        this.hairColor = hairColor;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(@RequestBody double gpa) {
        this.gpa = gpa;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(@RequestBody int uid) {
        this.uid = uid;
    }

};