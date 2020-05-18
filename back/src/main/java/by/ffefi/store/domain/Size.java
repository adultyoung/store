package by.ffefi.store.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class Size  implements Serializable {
    private String size;
    private String price;
    private String img;
}
