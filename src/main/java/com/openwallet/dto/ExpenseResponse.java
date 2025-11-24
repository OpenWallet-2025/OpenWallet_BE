package com.openwallet.dto;

import com.openwallet.domain.Expense;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ExpenseResponse {
    private UUID id;
    private String title;
    private LocalDate date;
    private Long price;
    private String category;
    private String emotion;
    private String memo;
    private int satisfaction;

    public ExpenseResponse(Expense entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.date = entity.getDate();
        this.price = entity.getPrice();
        this.category = entity.getCategory().name();
        this.emotion = entity.getEmotion().name();
        this.memo = entity.getMemo();
        this.satisfaction = entity.getSatisfaction();
    }
}
