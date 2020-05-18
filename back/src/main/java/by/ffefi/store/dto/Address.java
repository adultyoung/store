package by.ffefi.store.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address implements Serializable {
    private String city;
    private String street;
    private String house;
    private String number;
}
