package entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Trade
 * @Description TODO
 * @Author Finger
 * @Date 12/31/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Trade {
    private int customerId;
    private int produceId;
    private int tradeId;
    private BigDecimal amount;
    private int produceNum;
}
