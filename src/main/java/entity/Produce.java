package entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Produce
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produce {
    private int produceId;
    private String name;
    private BigDecimal price;
    private Date produceDate;
    private String produceType;
    private String produceImg;
}
