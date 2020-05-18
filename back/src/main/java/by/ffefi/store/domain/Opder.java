package by.ffefi.store.domain;

import by.ffefi.store.dto.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "createdAt"})
@ToString(of = {"id", "sum"})
public class Opder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sum;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;

    private OrderStatus status;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "opder_item",
            joinColumns = { @JoinColumn(name = "opder_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id")
            }
    )
    private List<Item> items = new ArrayList<>();

    private String name;

    private String address;

    private String email;

    private String telephone;

    private String delivery;

    private String comment;

    private String quantities;

    private Long userId;

}
