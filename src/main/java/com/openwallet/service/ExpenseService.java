package com.openwallet.service;

import com.openwallet.Category;
import com.openwallet.Emotion;
import com.openwallet.domain.Expense;
import com.openwallet.dto.ExpenseRequest;
import com.openwallet.dto.ExpenseResponse;
import com.openwallet.dto.ExpenseUpdateRequest;
import com.openwallet.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseResponse save(ExpenseRequest request) {
        Expense saved = expenseRepository.save(request.toEntity());

        return new ExpenseResponse(saved);
    }

    public List<ExpenseResponse> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseResponse::new)
                .toList();
    }

    public ExpenseResponse update(UUID id, ExpenseUpdateRequest request) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 지출 내역을 찾을 수 없습니다: " + id));

        if (request.getTitle() != null) expense.setTitle(request.getTitle());
        if (request.getDate() != null) expense.setDate(request.getDate());
        if (request.getPrice() != null) expense.setPrice(request.getPrice());
        if (request.getCategory() != null) expense.setCategory(Category.valueOf(request.getCategory()));
        if (request.getEmotion() != null) expense.setEmotion(Emotion.valueOf(request.getEmotion()));
        if (request.getMemo() != null) expense.setMemo(request.getMemo());
        if (request.getSatisfaction() != null) expense.setSatisfaction(request.getSatisfaction());

        Expense updated = expenseRepository.save(expense);

        return new ExpenseResponse(updated);
    }

    public void delete(UUID id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("해당 지출 내역을 찾을 수 없습니다: " + id);
        }
        expenseRepository.deleteById(id);
    }
}
