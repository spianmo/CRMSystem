package entity;

import bean.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName User
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Level accountLevel;

    public enum Level implements BaseEnum {
        /**
         * 管理员
         */
        ADMIN(0,"管理员"),
        /**
         * 员工
         */
        EMPLOYEE(1,"员工"),
        /**
         * 客户
         */
        CUSTOMER(2,"客户");

        private final Integer value;
        private final String desc;

        Level(Integer value,String desc) {
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
