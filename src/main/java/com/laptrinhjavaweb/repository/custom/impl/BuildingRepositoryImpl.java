package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    //tao connection
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> getBuildings(BuildingDTO buildingDTO) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM building b ");
        if(buildingDTO.getStaffID()!=-1){
            sql.append(" join assignmentbuilding a on b.id = a.buildingid");
        }
        sql.append(" where 1=1 ");
        sql = buildSqlBuildingSearchComon(buildingDTO, sql);
        sql = buildingSqlBuildingSearchSpecial(sql, buildingDTO);
        if(buildingDTO.getStaffID()!=-1){
            sql.append(" and a.staffid = "+buildingDTO.getStaffID()+ " ");
        }
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
    private StringBuffer buildingSqlBuildingSearchSpecial(StringBuffer sql, BuildingDTO buildingDTO) {

        if (buildingDTO.getRentPriceFrom() != null) {
            sql.append("and" + buildingDTO.getRentPriceFrom() + "<=b.rentprice");
        } else if (buildingDTO.getRentPriceTo() != null) {
            sql.append("and" + buildingDTO.getRentPriceTo() + ">=b.rentprice");
        }
        if(buildingDTO.getRentPriceFrom() != null || buildingDTO.getRentPriceTo() != null){
            sql.append(" and EXISTS (SELECT * FROM rentarea (r WHERE  r.buildingid = b.id");
            if(buildingDTO.getRentPriceFrom() != null){
                sql.append("and r.value >= "+buildingDTO.getRentPriceFrom()+" ");
            }
            if(buildingDTO.getRentPriceTo() != null){
                sql.append("and r.value <= "+buildingDTO.getRentPriceTo()+" ");
            }
            sql.append("))");
        }

//        java 7
        /*if(buildingSearchBuilder.getTypes() != null){
            int lengthType = buildingSearchBuilder.getTypes().length;
            sql.append(" and (b.type like '%"+buildingSearchBuilder.getTypes()[0]+"%");
            for(int i=1;i<lengthType;i++){
                if(i>=1){
                    sql.append(" or b.type like '%"+buildingSearchBuilder.getTypes()[i]+"%");
                }
            }
            sql.append(")");
        }*/

        //Java 8
        if(buildingDTO.getTypes() != null && buildingDTO.getTypes().length>0){
            sql.append(" and (");
            String sqlType = Arrays.stream(buildingDTO.getTypes())
                    .map(item -> " b.type like '% "+ item + "%'")
                    .collect(Collectors.joining(" OR "));
            sql.append(sqlType);
            sql.append(" )");
        }


        return sql;
    }
    private StringBuffer buildSqlBuildingSearchComon(BuildingDTO buildingDTO, StringBuffer sql) {
        try {
            Field[] fields = BuildingDTO.class.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("nameEmployeeInCharge") && !field.getName().startsWith("rentArea")
                        && !field.getName().startsWith("rentPrice") && !field.getName().equals("types")) {
                    String fieldType = field.getType().getName();
                    field.setAccessible(true);
                    if (fieldType.equals("java.lang.String")) {
                        if (field.get(buildingDTO) != null) {
                            sql.append(" and b." + field.getName().toLowerCase() + " like '%"
                                    + field.get(buildingDTO).toString() + "%'");
                        }
                    } else if (fieldType.equals("java.lang.Integer")) {
                        if (field.get(buildingDTO) != null) {
                            sql.append(" and b." + field.getName().toLowerCase() + " = "
                                    + field.get(buildingDTO) + "");
                        }
                    } else if (fieldType.equals("java.lang.Double")) {
                        if (field.get(buildingDTO) != null) {
                            sql.append(" and b." + field.getName().toLowerCase() + " = "
                                    + field.get(buildingDTO));
                        }
                    }
                }
            }
            return sql;
        } catch (IllegalAccessException e) {
            return new StringBuffer();
        }

    }
}
