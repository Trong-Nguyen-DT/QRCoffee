package com.example.admin.convertor;

import com.example.admin.domain.Table;
import com.example.admin.entity.TableEntity;


public class TablesConvertor {

    public static Table toModel(TableEntity tableEntity){

        Table table = new Table();
         table.setId(tableEntity.getId());
         table.setName(tableEntity.getName());
         table.setStatus(tableEntity.isStatus());

         return table;

    }
}
