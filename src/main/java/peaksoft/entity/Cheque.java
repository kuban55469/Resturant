package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "cheques")
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_seq")
    @SequenceGenerator(name = "cheque_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal priceAverage;
    private LocalDate createdAd;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "cheques_menu_items",
            joinColumns = @JoinColumn(name = "cheque_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_items_id"))
    private List<MenuItem> menuItems ;

    public void addMenuIterm(MenuItem menuItem) {
        if (menuItems==null){
            menuItems=new ArrayList<>();
        }
        menuItems.add(menuItem);
    }
}