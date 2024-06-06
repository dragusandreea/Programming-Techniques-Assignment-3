package start;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionExample {
    public static List<String> retrieveProperties(Object object) {
           List<String> prop = new ArrayList<String>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(object);
                //System.out.println(field.getName() + "=" + value);
                prop.add(field.getName());

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return prop;
    }
}
