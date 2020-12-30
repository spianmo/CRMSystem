package entity;

import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Employee
 * @Description TODO
 * @Author Finger
 * @Date 12/28/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private int employeeId;
    private String name;
    private String produceType;
    private int departmentId;
    private BigDecimal salary;
}
