package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户服务，使用内存存储，便于示例演示。
 */
@Service
public class UserService {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public UserService() {
        // 初始化两条示例数据
        create(new UserCreateRequest("张三", "zhangsan@example.com"));
        create(new UserCreateRequest("李四", "lisi@example.com"));
    }

    /**
     * 查询全部用户，按 ID 升序返回。
     */
    public List<User> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(User::id))
                .toList();
    }

    /**
     * 按 ID 查询用户，不存在时抛出异常。
     */
    public User findById(Long id) {
        User user = store.get(id);
        if (user == null) {
            throw new ResourceNotFoundException("用户不存在, id=" + id);
        }
        return user;
    }

    /**
     * 创建用户。
     */
    public User create(UserCreateRequest request) {
        long id = idGenerator.incrementAndGet();
        User user = new User(id, request.name(), request.email());
        store.put(id, user);
        return user;
    }
}
