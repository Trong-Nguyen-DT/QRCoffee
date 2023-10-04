package com.example.customer.converter;

import com.example.customer.domain.Table;
import com.example.customer.entity.TableEntity;


public class TablesConverter {

    public static Table toModel(TableEntity tableEntity){

        Table table = new Table();
         table.setId(tableEntity.getId());
         table.setName(tableEntity.getName());
         table.setStatus(tableEntity.isStatus());

         return table;

    }
}
