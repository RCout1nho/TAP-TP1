package tp01.repository;

import tp01.database.DataBaseConnection;
import tp01.dto.CreateRentDto;
import tp01.dto.RentWithTitleNameDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentRepository extends DataBaseConnection {
    private final Connection connection;
    private final TitleRepository titleRepository;

    public RentRepository(){
        this.connection = DataBaseConnection.getConnection();
        titleRepository = new TitleRepository();
    }

    public boolean createRent(CreateRentDto rent){
        try{
            Statement st = connection.createStatement();
            Integer remainingTitleQuantity = titleRepository.getTitleRemaingQuantity(rent.getTitle_id());
            if(remainingTitleQuantity>0){
                String query = String.format("INSERT INTO tap_db.rents (employee_id, client_id, title_id, start_date, end_date) VALUES (%d, %d, %d, '%s', '%s')",
                        rent.getEmployee_id(), rent.getClient_id(), rent.getTitle_id(), rent.getStart_date(), rent.getEnd_date()
                );
                String query1 = String.format("UPDATE tap_db.titles t SET t.quantity = t.quantity - 1 WHERE t.id = %d", rent.getTitle_id());
                st.executeUpdate(query);
                st.executeUpdate(query1);
                return true;
            }
            return false;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean returnARent(RentWithTitleNameDto rent){
        try{
            Statement st = connection.createStatement();
            String query1 = String.format("UPDATE tap_db.titles t SET t.quantity = t.quantity + 1 WHERE t.id = %d", rent.getTitle_id());
            String query2 = String.format("DELETE FROM tap_db.rents r WHERE r.id = %d", rent.getId());
            st.executeUpdate(query1);
            st.executeUpdate(query2);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<RentWithTitleNameDto> getAllByClientId(Integer id){
        try{
            List<RentWithTitleNameDto> rents = new ArrayList<>();

            Statement st = connection.createStatement();
            String query = String.format("SELECT r.*, t.name as title_name from tap_db.rents r left join tap_db.titles t ON t.id = r.title_id WHERE r.client_id = %d", id);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                rents.add(new RentWithTitleNameDto(
                        rs.getInt("id"),
                        rs.getInt("employee_id"),
                        rs.getInt("client_id"),
                        rs.getInt("title_id"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("title_name")
                ));
            }
            return rents;
        }catch (Exception e){
            return null;
        }
    }
}
