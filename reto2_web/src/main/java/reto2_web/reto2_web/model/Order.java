package reto2_web.reto2_web.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public static String PENDING = "Pendiente";
    public static String APROVED = "Aprobada";
    public static String REJECTED = "Rechazada";
    
    @Id
    private Integer id;
    private Date registerDay;
    private String status;
    private User salesMan;
    
    private Map<String, Vegetarian> products;
    private Map<String, Integer> quantities;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getRegisterDay() {
        return registerDay;
    }
    public void setRegisterDay(Date registerDay) {
        this.registerDay = registerDay;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public User getSalesMan() {
        return salesMan;
    }
    public void setSalesMan(User salesMan) {
        this.salesMan = salesMan;
    }
    
    
}
