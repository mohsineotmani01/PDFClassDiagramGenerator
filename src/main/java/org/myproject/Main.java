package org.myproject;

import org.myproject.utils.FileUtils;
import org.myproject.utils.GraphUtils;


import java.io.IOException;


public class Main {

    public static void main(String[] args) {


     String markdownCode = GraphUtils.createGraph("org.myproject.source");
        FileUtils.writeToMarkdownFile("src/markdown/mermaid1.md", markdownCode);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\nodejs\\npx.cmd", "mmdc", "-i", "src/markdown/mermaid1.md", "-o", "src/output/mermaid.pdf");
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
