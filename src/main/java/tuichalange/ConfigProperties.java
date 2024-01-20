package tuichalange;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    public static final String BASE_URL_PROP = "api.base.url";


    @SneakyThrows
    public static String getProperty(String property) {

        Properties properties = new Properties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("app.properties");
        properties.load(stream);

        return properties.getProperty(property);
    }
}
