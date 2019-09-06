package com.silv.api.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank(message = "名字不能为空")
    private String name; // 数字或字母，只能为4~6位

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 11, message = "密码需在6-11位之间")  // 先判断不为空，再判断长度
    private String password;

    @AssertTrue(message = "type类型只能为true")
    private boolean type;

    @NotNull(message = "文件不能为空")
    private MultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
