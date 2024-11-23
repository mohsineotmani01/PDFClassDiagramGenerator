package org.myproject.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassUtils {

  public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
    InputStream stream = ClassLoader
            .getSystemClassLoader()
            .getResourceAsStream(packageName.replaceAll("[.]", "/"));

    //vérification que le chemin est exist
    if (stream == null) {
      System.out.println("Le répertoire (Path ) n'a pas été trouvé: " + stream);
      System.exit(1);
      return Collections.emptySet();
    }
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      return reader
              .lines()
              .filter(line -> line.endsWith(".class"))
              .map(line -> getClass(line, packageName))
              .collect(Collectors.toSet());
    }

    private static Class getClass (String className, String packageName){
      try {
        return Class.forName(
                packageName + "." + className.substring(0, className.lastIndexOf('.'))
        );
      } catch (ClassNotFoundException e) {
        // handle the exception
        System.out.println("Class " + className + " not found");
      }
      return null;
    }

}
