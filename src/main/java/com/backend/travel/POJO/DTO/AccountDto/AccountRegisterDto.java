package com.backend.travel.POJO.DTO.AccountDto;

import com.backend.travel.ValidtorAnnotation.Phone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户注册账号
 */
@Data
public class AccountRegisterDto implements Serializable {
    private static final long serialVersionUID = -5003258358910927175L;

    @NotBlank( message = "账号不能为空" )
    @Length( min = 4, max = 8, message = "账号长度在4-位" )
    private String userAccount;

    @NotBlank( message = "密码不能为空" )
    @Length( min = 6, max = 12, message = "密码长度在6-12位" )
    private String userPassword;

    @NotBlank( message = "手机号不能为空" )
    @Phone
    private String userPhoneNum;

    @NotBlank( message = "密码不能为空" )
    @Length( min = 6, max = 12, message = "密码长度在6-12位" )
    private String checkPassword;
}
