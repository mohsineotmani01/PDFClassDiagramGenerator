package org.myproject;
import org.myproject.utils.FileUtils;
import org.myproject.utils.GraphUtils;
import java.io.IOException;
import java.util.Scanner;

import org.myproject.generateSource.JavaFileGenerator;

public class Main {

    public static void main(String[] args) {

        ///generate class
        JavaFileGenerator generator = new JavaFileGenerator();
        generator.run();


    }}
        /*


 ///generate uml

        Scanner scanner = new Scanner(System.in);
        String nameFile;

        do {
            System.out.print("Saisir un nom valide de votre diagrame : ");
            nameFile = scanner.nextLine();
        } while (nameFile.isEmpty());
        String nameFNotSpace = nameFile.replaceAll("\\s+", "");
        String markdownCode = GraphUtils.createGraph("org.myproject.source");
        FileUtils.writeToMarkdownFile("src/markdown/"+ nameFNotSpace + ".md" , markdownCode);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\nodejs\\npx.cmd", "mmdc", "-i", "src/markdown/"+ nameFNotSpace + ".md", "-o", "src/output/"+ nameFNotSpace +".pdf");
            processBuilder.inheritIO();

            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Diagramme généré et sauvegardé sous output.pdf.");
            } else {
                System.err.println("Une erreur est survenue lors de la génération du diagramme.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
*/