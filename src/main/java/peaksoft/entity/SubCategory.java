package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_category_seq")
    @SequenceGenerator(name = "sub_category_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;


    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH},fetch = FetchType.LAZY)
    private MenuItem menuItem;
}