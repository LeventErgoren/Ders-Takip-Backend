package com.ders.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableCalismaSuresiRequest {

    @NotNull(message = "İd Null olamaz")
    private Long id;

    private int page;

    @NotEmpty(message = "Sort boş olamaz")
    private String sort;
}

