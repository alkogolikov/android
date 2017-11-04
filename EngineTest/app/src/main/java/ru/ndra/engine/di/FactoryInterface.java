package ru.ndra.engine.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

interface FactoryInterface {
    Object create(String token);
}
