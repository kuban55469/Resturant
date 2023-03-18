package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "meu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meu_item_seq")
    @SequenceGenerator(name = "meu_item_seq")
    @Column(name = "id", nullable = false)
    private Long id;

}