package repository;

import database.DataBaseConnection;
import dto.CreateTitleDto;
import view.Title;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TitleRepository {
    private final Connection connection;

    public TitleRepository() {
        this.connection = DataBaseConnection.getConnection();
    }

    public void createTitle(CreateTitleDto title){
        try{
            Statement st = connection.createStatement();
            String query = String.format("INSERT INTO tap_db.titles (name, type, quantity, max_period_rent) VALUES ('%s', '%s', %d, %d)",
                    title.getName(), title.getType(), title.getQuantity(), title.getMaxPeriodOfRent()
            );
            st.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
