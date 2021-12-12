package ru.job4j.dream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private volatile static Config config = null;
    private final Properties properties = new Properties();

    private Config() throws IOException {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("files.properties")) {
            properties.load(in);
        }
    }

    public static Config getConfig() throws IOException {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
