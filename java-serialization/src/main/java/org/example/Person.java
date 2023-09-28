package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 4227172745956908990L;

    private String name;
    private Integer age;
    private transient String occupation;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.occupation = defineOccupationByAge(this.age);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.occupation = defineOccupationByAge(this.age);
    }

    private String defineOccupationByAge(Integer age) {
        if (0 <= age && age < 3) {
            return "сидит дома";
        } else if (3 <= age && age < 7) {
            return "ходит в детский сад";
        } else if (7 <= age && age < 18) {
            return "учится в школе";
        } else if (18 <= age && age < 23) {
            return "учится в институте";
        } else if (23 <= age && age < 65) {
            return "работает";
        } else {
            return "на пенсии";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
