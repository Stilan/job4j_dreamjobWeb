package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private static final Store INST = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job",
                "Тех.стек: Java, JavaScript, Spring, Hibernate, ReactJS.",  "1.12.2021"));
        posts.put(2, new Post(2, "Middle Java Job",
                "Наш основной стек Java 8, Spring Boot, PostgreSQL, JUnit, Maven, Gradle, Git, Docker и Kubernetes ", "30.11.2021"));
        posts.put(3, new Post(3, "Senior Java Job",
                "Опыт с Java Core, Spring Framework, Hibernate", "29.11.2021"));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
