package repository;

import database.DataBaseConnection;
import dto.CreateRent;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RentRepository {
    private final Connection connection;

    public RentRepository(){
        this.connection = DataBaseConnection.getConnection();
    }

    public void createRent(CreateRent rent){
        try{
            Statement st = connection.createStatement();
            String query = String.format("INSERT INTO tap_db.rents (employee_id, client_id, title_id, start_date, end_date) VALUES (%d, %d, %d, '%s', '%s')",
                    rent.getEmployee_id(), rent.getClient_id(), rent.getTitle_id(), rent.getStart_date(), rent.getEnd_date()
                    );
            st.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
