package Utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties props = new Properties();

    /**
     * static initializer runs once when the class is first loaded. It runs before methods and after static fields
     * It loads config.properties from class path - looks for first matching config.properties class
     * It then loads these (key-value pairs) into the 'props' object
     * The try-catch is for handling exceptions: when config.proprties cannot be found or read, a RuntimeException is
     * thrown which stops the tests.
     */
    static {
        try (InputStream is = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(is); // file is optional when CI injects via -D/ENV
        } catch (Exception e) {throw new RuntimeException("Failed to load config.properties", e);}
    }
    /**
     * Priority:
     * 1) JVM System property:   -DbaseUrl=..., -Duser.standard_user=...
     * 2) config.properties on classpath
     */
    public static String get(String key) {
        // 1) -Dkey=value
        String sys = System.getProperty(key);
        if (sys != null) return sys;

        // 2) properties file
        return props.getProperty(key);
    }
    public static String baseUrl() { return get("baseUrl"); }
    public static String browser() { return get("browser"); }
    public static boolean headed() { return Boolean.parseBoolean(get("headed")); }
    public static String username(String userType) {
        System.out.println("This is the usertype returned: "+get("user." + userType));
        return get("user." + userType); }
    public static String password() { return get("password"); }
}