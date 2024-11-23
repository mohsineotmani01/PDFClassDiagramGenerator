package org.myproject.generateSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandler {

    public void generateFile(EntityType entityType, String name, List<String> relations, List<String> attributes) {
        // Chemin spécifique pour le fichier
        String directoryPath = "src/main/java/org/myproject/source"; // Spécifiez ici votre chemin
        File directory = new File(directoryPath);

        // Vérification si le répertoire existe, sinon création
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Le nom du fichier
        String fileName = directoryPath + name + ".java";  // Ajout du chemin avant le nom du fichier
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {
            // Création de l'en-tête du fichier selon le type
            String header = switch (entityType) {
                case CLASS -> "public class " + name;
                case ABSTRACT_CLASS -> "public abstract class " + name;
                case INTERFACE -> "public interface " + name;
            };

            if (!relations.isEmpty()) {
                header += " " + String.join(" ", relations);
            }

            writer.write(header + " {\n\n");

            // Ajout des attributs (si applicable)
            if (!attributes.isEmpty() && (entityType == EntityType.CLASS || entityType == EntityType.ABSTRACT_CLASS)) {
                for (String attr : attributes) {
                    writer.write("    private " + attr + "\n");
                }
                writer.write("\n");
            }

            writer.write("    // Ajoutez vos méthodes ici\n");
            writer.write("}\n");

            System.out.println("Le fichier " + fileName + " a été généré avec succès.");
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la création du fichier : " + e.getMessage());
        }
    }
}
