package tp01.repository;

import tp01.database.DataBaseConnection;
import tp01.dto.CreateTitleDto;
import tp01.model.Title;

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

    public boolean updateTitle(Title title){
        try{
            Statement st = connection.createStatement();
            String query = String.format("UPDATE tap_db.titles t SET t.name='%s', t.type='%s', t.quantity=%d, t.max_period_rent=%d WHERE t.id = %d",
                   title.name, title.type, title.quantity, title.maxPeriodOfRent, title.id
            );
            st.executeUpdate(query);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean deleteTitle(Integer titleId){
        try{
            Statement st = connection.createStatement();
            String query = String.format("DELETE FROM tap_db.titles t WHERE t.id = %d",titleId);
            st.executeUpdate(query);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public Integer getTitleRemaingQuantity(Integer titleId){
        try {
            Statement st = connection.createStatement();
            String query = String.format("SELECT t.quantity FROM tap_db.titles t WHERE t.id = %d", titleId);
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                return rs.getInt("quantity");
            }
            return null;
        }catch (Exception e){
            return null;
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

    public List<Title> getAllAvailable(){
        try{
            List<Title> titles = new ArrayList<>();

            Statement st = connection.createStatement();
            String query = "SELECT * FROM tap_db.titles t WHERE t.quantity > 0";
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
