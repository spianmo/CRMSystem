package entity;

import java.sql.Date;

import bean.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Task
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    private int taskId;
    private int employeeId;
    private Date taskTime;
    private String taskDesc;
    private int customerNum;
    private TaskStatus taskStatus;

    public enum TaskStatus implements BaseEnum {
        /**
         * 非常好
         */
        VERYGOOD(0,"非常好"),
        /**
         * 好
         */
        GOOD(1,"好"),
        /**
         * 一般
         */
        COMMON(2, "一般"),
        /**
         * 差
         */
        BAD(3, "差"),
        /**
         * 非常差
         */
        VERYBAD(4, "非常差"),
        /**
         * 绩效未复核
         */
        NOPROCESS(5, "绩效未复核");

        private final Integer value;
        private final String desc;

        TaskStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getCode() {
            return this.value;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }
}
