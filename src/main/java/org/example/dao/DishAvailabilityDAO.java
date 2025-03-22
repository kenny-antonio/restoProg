package org.example.dao;

import org.example.db.Datasource;
import org.example.entity.DishAvailability;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DishAvailabilityDAO implements CrudOperation<DishAvailability>{
        public Datasource dataSource;

        public DishAvailabilityDAO() {
            this.dataSource = new Datasource();
        }

        @Override
        public List<DishAvailability> findAll() {
            String sql = "select dish_name, available_stock from dish_availability";
            try(Connection conn = dataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)){
                try(ResultSet rs = statement.executeQuery()){
                    List<DishAvailability> dishAvailabilities = new ArrayList<>();
                    while(rs.next() == true){
                        DishAvailability dishAvailability = new DishAvailability();
                        dishAvailability.setName(rs.getString("dish_name"));
                        dishAvailability.setAvailability(rs.getDouble("available_stock"));
                        dishAvailabilities.add(dishAvailability);
                    }
                    return dishAvailabilities;
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
}