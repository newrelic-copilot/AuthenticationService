package Model;
import java.util.Date;

public class MyModel {
    private Date dateField;

    public MyModel() {
        // Default constructor
    }

    public MyModel(Date dateField) {
        this.dateField = dateField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
    this.dateField = dateField;}
}