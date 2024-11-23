package org.myproject.generateSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MethodHandler {

    // Gestion des méthodes
    public static void collectMethods(Scanner scanner, List<String> methods) {
        while (true) {
            System.out.print("Voulez-vous ajouter une méthode ? (y/n) : ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("n")) {
                break; // Fin de la collecte des méthodes
            }

            if (answer.equalsIgnoreCase("y")) {
                // Nom de la méthode
                System.out.print("Entrez le nom de la méthode : ");
                String methodName = scanner.nextLine().trim();

                // Type de retour
                DataType returnType = chooseDataType(scanner, "type de retour");
                if (returnType == null) {
                    System.out.println("Choix invalide pour le type de retour. La méthode ne sera pas ajoutée.");
                    continue;
                }

                // Visibilité
                System.out.print("Choisissez la visibilité de la méthode (public, private, protected) : ");
                String visibility = scanner.nextLine().trim();

                // Paramètres
                List<String> parameters = collectParameters(scanner);

                // Signature de la méthode
                StringBuilder methodSignature = new StringBuilder();
                methodSignature.append("    ").append(visibility).append(" ").append(returnType.getTypeName()).append(" ").append(methodName).append("(");

                if (!parameters.isEmpty()) {
                    methodSignature.append(String.join(", ", parameters));
                }

                methodSignature.append(") {\n");
                methodSignature.append("        // Implémentation de la méthode\n");
                methodSignature.append("    }\n");

                // Ajouter la méthode générée à la liste
                methods.add(methodSignature.toString());
            }
        }
    }

    // Gestion des paramètres de la méthode
    private static List<String> collectParameters(Scanner scanner) {
        List<String> parameters = new ArrayList<>();
        while (true) {
            System.out.print("Voulez-vous ajouter un paramètre à la méthode ? (y/n) : ");
            String addParam = scanner.nextLine().trim();

            if (addParam.equalsIgnoreCase("n")) {
                break; // Fin de l'ajout des paramètres
            }

            if (addParam.equalsIgnoreCase("y")) {
                DataType paramType = chooseDataType(scanner, "type du paramètre");
                if (paramType == null) {
                    System.out.println("Choix invalide pour le type de paramètre. Ce paramètre sera ignoré.");
                    continue;
                }

                System.out.print("Entrez le nom du paramètre : ");
                String paramName = scanner.nextLine().trim();

                parameters.add(paramType.getTypeName() + " " + paramName);
            }
        }
        return parameters;
    }

    // Méthode utilitaire pour choisir un type à l'aide de DataType
    private static DataType chooseDataType(Scanner scanner, String context) {
        System.out.println("Choisissez un " + context + " :");
        DataType.displayChoices();

        System.out.print("Votre choix (1-" + DataType.values().length + ") : ");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);
            return DataType.fromChoice(choice);
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        }

        return null;
    }
}
