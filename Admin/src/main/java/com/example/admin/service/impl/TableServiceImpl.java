package com.example.admin.service.impl;

import com.example.admin.convertor.TablesConvertor;
import com.example.admin.domain.Order;
import com.example.admin.domain.Table;
import com.example.admin.entity.TableEntity;
import com.example.admin.repository.TableRepository;
import com.example.admin.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class TableServiceImpl implements TableService {
     @Autowired
     private TableRepository tableRepository;


    @Override
    public List<Table> getAllTables() {
        return tableRepository.findAll().stream().map(TablesConvertor::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Table> getAllTableFalse() {
        return tableRepository.findAllByStatusFalse().stream().map(TablesConvertor::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Table> getAllTableTrue() {
        return tableRepository.findAllByStatusTrue().stream().map(TablesConvertor::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Table> getAllTableYellow(List<Order> orderConfirmedFalse) {
        List<Table> tables = getAllTableTrue();
        List<Table> tableReturn = new ArrayList<>();
        for (Table table:tables) {
            boolean b = false;
            for (Order order:orderConfirmedFalse) {
                if (Objects.equals(table.getId(), order.getTableId())) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                tableReturn.add(table);
            }
        }
        return tableReturn;
    }

    @Override
    public Table getById(Long id) {
        return TablesConvertor.toModel(tableRepository.findById(id).orElseThrow());
    }

    @Override
    public void switchTable(Long id, Long switchId) {
        TableEntity table = tableRepository.findById(id).orElseThrow();
        table.setStatus(false);
        tableRepository.save(table);
        TableEntity newTable = tableRepository.findById(switchId).orElseThrow();
        newTable.setStatus(true);
        tableRepository.save(newTable);
    }

    @Override
    public void checkoutTable(Long tableId) {
        TableEntity table = tableRepository.findById(tableId).orElseThrow();
        table.setStatus(false);
        tableRepository.save(table);
    }
}
