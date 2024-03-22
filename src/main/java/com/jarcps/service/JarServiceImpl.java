package com.jarcps.service;

import com.jarcps.model.Jar;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JarServiceImpl implements JarService {

    private final Map<Integer, Jar> jars = new HashMap<>();

    public JarServiceImpl() {
        // Initialize mock jars
        jars.put(1, new Jar(1, "Sugar", 500.0));
    }

    @Override
    public Jar getJarById(int id) {
        return jars.get(id);
    }

    @Override
    public void adjustQuantity(int id, double newQuantity) {
        Jar jar = jars.get(id);
        if (jar != null) {
            jar.setQuantity(newQuantity);
            jars.put(id, jar);
        }
    }
}
