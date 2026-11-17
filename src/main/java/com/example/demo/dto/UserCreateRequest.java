package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 创建用户的请求体。
 *
 * @param name  用户姓名
 * @param email 用户邮箱
 */
public record UserCreateRequest(
        @NotBlank(message = "name 不能为空")
        @Size(max = 32, message = "name 长度不能超过 32")
        String name,

        @NotBlank(message = "email 不能为空")
        @Email(message = "email 格式不正确")
        String email
) {
}
