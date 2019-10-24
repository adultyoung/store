package by.ffefi.store.domain;

import by.ffefi.store.dto.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;


@EqualsAndHashCode(of = {"id"})
@Data
@MappedSuperclass
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String img;

    @ColumnDefault(value = "true")
    private boolean inStock;

    private String price;

    private ItemType type;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "item_size", joinColumns = @JoinColumn(name = "item_id"))
    private Set<String> sizes;

    private String company;
}
