package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomerEntity> getCustomers(CustomerDTO customerDTO) {
        StringBuffer sql = new StringBuffer("SELECT * FROM customer c ");
        if (customerDTO.getStaffID() != null && customerDTO.getStaffID()!=-1) {
            sql.append(" JOIN assignmentcustomer a ON c.id = a.customerid ");
        }
        sql.append("WHERE 1=1 ");
        if (customerDTO.getStaffID() != null && customerDTO.getStaffID()!=-1) {
            sql.append(" and a.staffid = "+customerDTO.getStaffID());
        }
        sql = builSqlCustomerSearch(customerDTO, sql);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    public StringBuffer builSqlCustomerSearch(CustomerDTO customerDTO, StringBuffer sql) {
        try {
            Field[] fields = CustomerDTO.class.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("staffID")) {
                    String fieldType = field.getType().getName();
                    field.setAccessible(true);
                    if (fieldType.equals("java.lang.String")) {
                        if (field.get(customerDTO) != null && !field.get(customerDTO).equals("")) {
                            sql.append(" and c." + field.getName().toLowerCase() + " like '%"
                                    + field.get(customerDTO).toString() + "%'");
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
