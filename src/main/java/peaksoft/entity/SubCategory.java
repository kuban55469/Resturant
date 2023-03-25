package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_category_seq")
    @SequenceGenerator(name = "sub_category_seq", allocationSize = 1)
    private Long id;
    private String name;


    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = FetchType.LAZY)
    private Category category;


    @OneToMany(mappedBy = "subCategory", cascade = {MERGE, REFRESH, DETACH}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem) {
        if (menuItem == null){
            menuItems = new ArrayList<>();
        }
        menuItems.add(menuItem);
    }
}