package repository;

import database.DataBaseConnection;
import dto.CreateTitleDto;
import model.Title;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Title> getAll(){
        try{
            List<Title> titles = new ArrayList<>();

            Statement st = connection.createStatement();
            String query = "SELECT * FROM tap_db.titles";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Title title = new Title(rs.getInt("id"), rs.getString("name"),
                        rs.getString("type"), rs.getInt("quantity"),
                        rs.getInt("max_period_rent"));
                titles.add(title);
            }
            return titles;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
