package per.ccm.ygmall.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * 更新密码传输数据
 * */
public class UpdatePasswordDTO extends BaseDTO {

    @Schema(description = "原密码")
    private String password;

    @Schema(description = "新密码")
    private String newPassword;

    public UpdatePasswordDTO() {
    }

    public UpdatePasswordDTO(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String oldPassword) {
        this.password = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordDTO{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
