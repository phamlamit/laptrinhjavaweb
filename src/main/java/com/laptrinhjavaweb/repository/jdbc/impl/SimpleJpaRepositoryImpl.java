package com.laptrinhjavaweb.repository.jdbc.impl;

import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.JpaRepository;
import com.laptrinhjavaweb.util.ResultSetMapper;

public class SimpleJpaRepositoryImpl<T> implements JpaRepository<T> {

    private Class<T> zClass;

    public SimpleJpaRepositoryImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) t;
        Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
    }

    @Override
    public Long save(Object object) {
        Long buildingId = null;
        Connection conn = null;
        PreparedStatement rs = null;
        ResultSet result = null;
        try {
            conn = EntityManagerFactory.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = buildSqlInsert();

            rs = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for (Field feild : object.getClass().getDeclaredFields()) {
                feild.setAccessible(true);
                rs.setObject(index, feild.get(object));
                index++;
            }
            rs.executeUpdate();
            conn.commit();
            result = rs.getGeneratedKeys();

            if (result.next()) {
                buildingId = result.getLong(1);
            }

        } catch (SQLException | IllegalAccessException e) {
            // TODO Auto-generated catch block

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } finally {

            try {
                if (result != null) {
                    result.close();
                }
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();

            }

        } // end try
        return buildingId;

    }

    private String buildSqlInsert() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) t;
        Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class)) {
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        StringBuilder fields = new StringBuilder("");
        StringBuilder params = new StringBuilder("");
        for (Field field : zClass.getDeclaredFields()) {
            if (fields.length() > 1) {
                fields.append(",");
                params.append(",");
            }
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                fields.append(column.name());
                params.append("?");
            }

        }
        String sql = "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
        return sql;
    }

    @Override
    public List<T> fillAll() {
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        List<T> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            conn = EntityManagerFactory.getInstance().getConnection();

            stmt = conn.createStatement();
            Type t = getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) t;
            Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
            String tableName = "";
            if (zClass.isAnnotationPresent(Table.class)) {
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }

            String sql = "SELECT * FROM " + tableName + " where 1=1";
            rs = stmt.executeQuery(sql);
            return resultSetMapper.mapRow(rs, zClass);

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public T findById(long id) {
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = EntityManagerFactory.getInstance().getConnection();

            String tableName = "";
            Type t = getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) t;
            Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
            if (zClass.isAnnotationPresent(Table.class)) {
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }

            String sql = "SELECT * FROM " + tableName + " where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            List<T> result = resultSetMapper.mapRow(rs, zClass);
            return (result != null ? result.get(0) : null);

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<T> fillAll(String sql) {
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        List<T> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            conn = EntityManagerFactory.getInstance().getConnection();

            stmt = conn.createStatement();
            Type t = getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) t;
            Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
            String tableName = "";
            if (zClass.isAnnotationPresent(Table.class)) {
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            rs = stmt.executeQuery(sql);
            return resultSetMapper.mapRow(rs, zClass);

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        T result = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = EntityManagerFactory.getInstance().getConnection();

            String tableName = "";
            Type t = getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) t;
            Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
            if (zClass.isAnnotationPresent(Table.class)) {
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }

            String sql = "DELETE FROM " + tableName + " where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {

                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();
            }
        }

    }

    @Override
    public Long update(Object object) {
        Connection conn = null;
        PreparedStatement rs = null;
        ResultSet result = null;
        Object id = null;
        try {
            conn = EntityManagerFactory.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = buildingSqlUpdate();

            rs = conn.prepareStatement(sql);
            int index = 1;
            for (Field feild : object.getClass().getDeclaredFields()) {
                if (!feild.getName().equals("id")) {
                    feild.setAccessible(true);
                    rs.setObject(index, feild.get(object));
                    index++;
                } else {
                    id = feild.get(object);
                }
            }
            rs.setObject(index, id);
            rs.executeUpdate();
            conn.commit();

        } catch (SQLException | IllegalAccessException e) {
            // TODO Auto-generated catch block

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } finally {

            try {
                if (result != null) {
                    result.close();
                }
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();

            }

        } // end try
        return (long) id;

    }

    private String buildingSqlUpdate() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) t;
        Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class)) {
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        StringBuilder sqlUpdate = new StringBuilder("");
        for (Field field : zClass.getDeclaredFields()) {
            if (!field.getName().equals("id") && field.isAnnotationPresent(Column.class)) {
                if (sqlUpdate.length() > 1) {
                    sqlUpdate.append(" , ");
                }
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    sqlUpdate.append(column.name());
                    sqlUpdate.append(" = ?");
                }
            }

        }
        String sql = "UPDATE " + tableName + " SET " + sqlUpdate.toString() + " WHERE id = ?";
        return sql;
    }

    @Override
    public List<T> fillAll(Long buildingId) {
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        List<T> result = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = EntityManagerFactory.getInstance().getConnection();

            String tableName = "";
            Type t = getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) t;
            Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
            if (zClass.isAnnotationPresent(Table.class)) {
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }

            String sql = "SELECT * FROM " + tableName + " where buildingid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, buildingId);
            rs = stmt.executeQuery();
            result = resultSetMapper.mapRow(rs, zClass);
            return result;

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();
            }
        }
        return result;
    }
}
