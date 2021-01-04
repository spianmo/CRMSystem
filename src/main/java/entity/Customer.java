package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Customer
 * @Description TODO
 * @Author Finger
 * @Date 12/28/2020
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private int customerId;
    private int employeeId;
    private String name;
    private String address;
    private int credit;
    private String phone;
    private int userId;
}
