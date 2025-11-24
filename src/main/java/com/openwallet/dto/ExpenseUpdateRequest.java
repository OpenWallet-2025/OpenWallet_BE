package com.openwallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseUpdateRequest {
    private String title;
    private LocalDate date;
    private Long price;
    private String category;
    private String emotion;
    private String memo;
    private Integer satisfaction;
}