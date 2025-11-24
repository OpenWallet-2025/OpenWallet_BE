package com.openwallet.dto;

import com.openwallet.Category;
import com.openwallet.Emotion;
import com.openwallet.domain.Expense;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseRequest {

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    private String title;

    @NotNull(message = "날짜를 입력해야 합니다.")
    private LocalDate date;

    @NotNull(message = "금액을 입력해야 합니다.")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private Long price;

    @NotBlank(message = "카테고리는 필수값입니다.")
    private String category;

    @NotBlank(message = "감정(emotion)은 필수값입니다.")
    private String emotion;

    private String memo;

    @Min(value = 1, message = "만족도는 최소 1 이상이어야 합니다.")
    @Max(value = 5, message = "만족도는 최대 5 이하이어야 합니다.")
    private int satisfaction;

    public Expense toEntity() {
        return Expense.builder()
                .title(title)
                .date(date)
                .price(price)
                .category(Category.valueOf(category))
                .emotion(Emotion.valueOf(emotion))
                .memo(memo)
                .satisfaction(satisfaction)
                .build();
    }
}
