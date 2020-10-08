package com.laptrinhjavaweb.repository.jdbc.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.jdbc.CustomerRepository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryIml extends SimpleJpaRepositoryImpl<CustomerEntity> implements CustomerRepository {
    @Override
    public List<CustomerEntity> getCustomer(CustomerSearchBuilder builder) {
        List<CustomerEntity> result = new ArrayList<>();
        Connection conn = null;


        conn = EntityManagerFactory.getInstance().getConnection();

        StringBuffer sql = new StringBuffer("SELECT * FROM customer c ");
        if (builder.getNameStaffIncharge() != null) {
            sql.append(" JOIN assignmentcustomer ON c.id = assignmentcustomer.customerid ");
            sql.append(" JOIN user ON user.id = assignmentcustomer.staffid and user.fullname like '" + builder.getNameStaffIncharge() + "' ");
        }
        sql.append("WHERE 1=1 ");
        sql = builSqlCustomerSearch(builder, sql);

        result = fillAll(sql.toString());


        return result;
    }

    private StringBuffer builSqlCustomerSearch(CustomerSearchBuilder builder, StringBuffer sql) {
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("nameStaffInchage")) {
                    if (field.get(builder) != null) {
                        sql.append(" and b." + field.getName().toLowerCase() + "like '%"
                                + field.get(builder).toString() + "%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
