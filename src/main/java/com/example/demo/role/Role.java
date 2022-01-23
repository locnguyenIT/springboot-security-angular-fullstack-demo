package com.example.demo.role;

import com.example.demo.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Role {
    @Id //id will be primary key of table student
    @SequenceGenerator(name = "role_sequence",sequenceName = "role_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence") //use sequence is just defined above
    private int id;

    @Enumerated(EnumType.STRING)
    private EnumRole name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<User> user = new ArrayList<>();

    public Role() {
    }

    public Role(EnumRole name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(EnumRole name) {
        this.name = name;
    }

    public EnumRole getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
