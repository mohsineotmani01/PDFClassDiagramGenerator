package org.myproject.generateSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class FileHandler {

    public void generateFile(EntityType entityType, String name, List<String> relations, List<String> attributes, List<String> methods,  List<String> annotations) {
        // Chemin spécifique pour le fichier
        String directoryPath = "src/main/java/org/myproject/source/"; // Ajout d'un '/' à la fin
        File directory = new File(directoryPath);

        // Vérification si le répertoire existe, sinon création
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Le nom complet du fichier
        String fileName = directoryPath + name + ".java";
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package org.myproject.source ; \n\n");
            writer.write(" import org.myproject.annotations.ChildClass; \n");
            writer.write("import org.myproject.annotations.SuperClass;  \n\n");
            // Ajouter les annotations (si présentes)
            if (!annotations.isEmpty()) {
                for (String annotation : annotations) {
                    writer.write(annotation + "\n");
                }
            }

            // Création de l'en-tête du fichier selon le type
            String header = switch (entityType) {
                case CLASS -> "public class " + name;
                case ABSTRACT_CLASS -> "public abstract class " + name;
                case INTERFACE -> "public interface " + name;
            };

            // Ajout des relations (extends/implements) si elles existent
            if (!relations.isEmpty()) {
                header += " " + String.join(" ", relations);
            }

            writer.write(header + " {\n\n");

            // Ajout des attributs (si applicable)
            if (!attributes.isEmpty() && (entityType == EntityType.CLASS || entityType == EntityType.ABSTRACT_CLASS)) {
                writer.write("    // Attributs\n");
                for (String attr : attributes) {
                    writer.write("    private " + attr + ";\n"); // Ajout d'un point-virgule
                }
                writer.write("\n");
            }

            // Ajout des méthodes (si applicable)
            if (!methods.isEmpty()) {
                writer.write("    // Méthodes\n");
                for (String method : methods) {
                    writer.write(method + "\n");
                }
                writer.write("\n");
            }

            writer.write("}\n");
            System.out.println("Le fichier " + fileName + " a été généré avec succès.");
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la création du fichier : " + e.getMessage());
        }
    }
}
