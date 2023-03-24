package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_seq")
    @SequenceGenerator(name = "menu_item_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private Boolean isVegetarian;


    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = FetchType.LAZY)
    private Restaurant restaurant;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {MERGE, REFRESH, DETACH}, optional = false)
    private SubCategory subCategory;


    @OneToOne(mappedBy = "menuItem", cascade = ALL, fetch = FetchType.EAGER, optional = false)
    private StopList stopList;

    @ManyToMany(mappedBy = "menuItems", cascade = { MERGE, REFRESH, DETACH}, fetch = FetchType.EAGER)
    private List<Cheque> cheques = new ArrayList<>();

    public void addCheque(Cheque cheque) {
        cheques.add(cheque);
    }


}