package com.example.customer.validator;

import com.example.customer.handler.TableEntityNotFoundException;
import com.example.customer.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableValidator {

    @Autowired
    private TableService tableService;

    public Long validateTable(String tb) {
        try {
            Long tableId = Long.parseLong(tb);
            return tableService.getTableById(tableId).getId();
        } catch (NumberFormatException e) {
            throw new TableEntityNotFoundException("Bàn không tồn tại");
        }
    }
}
