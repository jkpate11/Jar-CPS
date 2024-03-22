package com.jarcps.Data;

import com.jarcps.model.Jar;
import com.jarcps.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dummy {
    private static final Map<Long, User> users = new HashMap<>();
    private static final Map<Long, Jar> jars = new HashMap<>();

    static {
        
        users.put(1L, new User(1L, "user1@example.com", "password1"));

        
        jars.put(1L, new Jar(1, "Sugar", 800.0));
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public static User getUserById(Long id) {
        return users.get(id);
    }

    public static List<Jar> getAllJars() {
        return new ArrayList<>(jars.values());
    }

    public static Jar getJarById(Long id) {
        return jars.get(id);
    }
}
