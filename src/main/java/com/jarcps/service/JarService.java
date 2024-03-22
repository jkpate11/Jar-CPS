package com.jarcps.service;

import com.jarcps.model.Jar;

public interface JarService {

    Jar getJarById(int id);

    void adjustQuantity(int i, double newQuantity);
}
