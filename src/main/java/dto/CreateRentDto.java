package dto;

public class CreateRentDto {
    private final Integer employee_id;
    private final Integer client_id;
    private final Integer title_id;
    private final String start_date;
    private final String end_date;

    public CreateRentDto(Integer employee_id, Integer client_id, Integer title_id, String start_date, String end_date) {
        this.employee_id = employee_id;
        this.client_id = client_id;
        this.title_id = title_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public Integer getTitle_id() {
        return title_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }
}
