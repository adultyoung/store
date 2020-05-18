package by.ffefi.store.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;


@EqualsAndHashCode(of = {"id"})
@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pet;

    private String title;

    private String description;

    @ColumnDefault(value = "true")
    private boolean inStock;

    private String price;

    private String type;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item_size", joinColumns = @JoinColumn(name = "item_id"))
    private Set<Size> sizes;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "item_description", joinColumns = @JoinColumn(name = "item_id"))
    private Set<Description> fullDescription;

    private String company;

    private String size;
    private String age;

    @ColumnDefault(value ="1")
    private int quantity = 1;

    private String sizeOfItem;
}
