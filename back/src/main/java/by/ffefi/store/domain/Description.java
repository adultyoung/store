package by.ffefi.store.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class Description {
    @Column(columnDefinition = "varchar")
    private String features;

    @Column(columnDefinition = "varchar")
    private String fullDescription;
    @Column(columnDefinition = "varchar")
    private String consists;
    @Column(columnDefinition = "varchar")
    private String additives;
    @Column(columnDefinition = "varchar")
    private String analysis;
    @Column(columnDefinition = "varchar")
    private String microelements;
    @Column(columnDefinition = "varchar")
    private String additional;
    @Column(columnDefinition = "varchar")
    private String manufacturer;
    @Column(columnDefinition = "varchar")
    private String importer;
}
