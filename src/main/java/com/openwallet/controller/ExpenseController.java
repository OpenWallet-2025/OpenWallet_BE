package com.openwallet.controller;

import com.openwallet.dto.ExpenseRequest;
import com.openwallet.dto.ExpenseResponse;
import com.openwallet.dto.ExpenseUpdateRequest;
import com.openwallet.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse saved = expenseService.save(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<ExpenseResponse> getAll() {
        return expenseService.findAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable UUID id, @RequestBody ExpenseUpdateRequest request) {
        ExpenseResponse updated = expenseService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
