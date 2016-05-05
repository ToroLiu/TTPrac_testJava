import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = Main.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to fine " + filename);
                return;
            }

            prop.load(input);

            System.out.println("Case 1");
            System.out.println(prop.getProperty("fb.app"));

            System.out.println("Case 2");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Foo foo1 = gson.fromJson(prop.getProperty("fb.app"), Foo.class);
            System.out.println("Foo: " + foo1.getAppId() + " ," + foo1.getAppSecret());

            System.out.println("Case 3");
            String fbApps = prop.getProperty("fb.apps");
            Foo[] foo2 = gson.fromJson(fbApps, Foo[].class);
            for (Foo a : foo2) {
                System.out.println("Foo: " + a.getAppId() + " ," + a.getAppSecret());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
