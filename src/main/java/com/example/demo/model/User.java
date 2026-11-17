package com.example.demo.model;

/**
 * 用户实体。
 *
 * @param id    用户 ID
 * @param name  用户姓名
 * @param email 用户邮箱
 */
public record User(Long id, String name, String email) {
}
