package org.myproject.generateSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MethodHandler {

    // Gestion des méthodes d'une classe
    public static void collectMethods(Scanner scanner, List<String> methods) {
        while (true) {
            System.out.print("Voulez-vous ajouter une méthode ? (y/n) : ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("n")) {
                break; // Fin de la collecte des méthodes
            }

            if (answer.equalsIgnoreCase("y")) {
                // Collecte des informations de base de la méthode
                System.out.print("Entrez le nom de la méthode : ");
                String methodName = scanner.nextLine().trim();

                // Choix du type de retour avec DataTypeM
                DataTypeM returnType = chooseDataType(scanner, "Choisissez le type de retour :");

                if (returnType == null) {
                    System.out.println("Type de retour invalide. Recommencez.");
                    continue;
                }

                // Choix de la visibilité
                System.out.print("Choisissez la visibilité de la méthode (public, private, protected) : ");
                String visibility = scanner.nextLine().trim();

                // Collecte des paramètres
                List<String> parameters = collectParameters(scanner);

                // Construction de la méthode
                StringBuilder methodSignature = new StringBuilder();
                methodSignature.append("    ").append(visibility).append(" ").append(returnType.getTypeName()).append(" ").append(methodName).append("(");

                if (!parameters.isEmpty()) {
                    methodSignature.append(String.join(", ", parameters));
                }

                methodSignature.append(") {\n");
                methodSignature.append("        // Implémentation de la méthode\n");
                methodSignature.append("    }\n");

                methods.add(methodSignature.toString());
            }
        }
    }

    // Collecte des paramètres
    private static List<String> collectParameters(Scanner scanner) {
        List<String> parameters = new ArrayList<>();
        while (true) {
            System.out.print("Voulez-vous ajouter un paramètre à la méthode ? (y/n) : ");
            String addParam = scanner.nextLine().trim();

            if (addParam.equalsIgnoreCase("n")) {
                break; // Fin des paramètres
            }

            if (addParam.equalsIgnoreCase("y")) {
                // Choisir le type du paramètre
                DataTypeM paramType = chooseDataType(scanner, "Choisissez un type pour le paramètre :");

                if (paramType == null) {
                    System.out.println("Type de paramètre invalide. Recommencez.");
                    continue;
                }

                // Saisie du nom du paramètre
                System.out.print("Entrez le nom du paramètre : ");
                String paramName = scanner.nextLine().trim();

                parameters.add(paramType.getTypeName() + " " + paramName);
            }
        }
        return parameters;
    }

    // Méthode pour choisir un DataTypeM avec affichage des choix
    private static DataTypeM chooseDataType(Scanner scanner, String message) {
        System.out.println(message);
        DataTypeM.displayChoices(); // Affichage des choix via DataTypeM

        while (true) {
            System.out.print("Votre choix (1-5) : ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                DataTypeM dataType = DataTypeM.fromChoice(choice);
                if (dataType != null) {
                    return dataType;
                }
            } catch (NumberFormatException e) {
                // Ignore l'exception, redemande une entrée valide
            }
            System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }
}
