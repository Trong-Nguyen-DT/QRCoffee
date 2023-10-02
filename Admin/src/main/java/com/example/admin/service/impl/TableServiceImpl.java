package com.example.admin.service.impl;

import com.example.admin.convertor.TablesConvertor;
import com.example.admin.domain.Table;
import com.example.admin.repository.TableRepository;
import com.example.admin.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
