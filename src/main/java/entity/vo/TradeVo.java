package entity.vo;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName TradeVo
 * @Description TODO
 * @Author Finger
 * @Date 1/13/2021
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TradeVo {
    private int tradeId;
    private String produceName;
    private int customerId;
    private BigDecimal amount;
    private int produceNum;
    private Date tradeTime;
}
