package by.ffefi.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItemPageDto {
    private List items;
    private int currentPage;
    private int totalPages;
}
