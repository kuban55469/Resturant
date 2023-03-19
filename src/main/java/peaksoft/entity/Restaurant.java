package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.RestType;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    @SequenceGenerator(name = "restaurant_seq", allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String location;
    @Enumerated(EnumType.STRING)
    private RestType restType;
    private Integer numberOfEmployees;
    private Integer service;

    @OneToMany(mappedBy = "restaurant",cascade = ALL, orphanRemoval = true, fetch = EAGER)
    private List<User> users = new ArrayList<>();


    @OneToMany(mappedBy = "restaurant",cascade = ALL, orphanRemoval = true, fetch = EAGER)
    private List<MenuItem> menuItems = new ArrayList<>();


    public void addChef(User user) {
        users.add(user);
    }

    public void addWaiter(User user) {
        users.add(user);
    }

    public void addMenu(MenuItem menuItem) {
        menuItems.add(menuItem);
    }
}