
import java.util.Scanner;

public class Bovary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== LITERARY MARKOV ENGINE ===");
        System.out.println("Please select a corpus:");
        System.out.println("1. Gustave Flaubert - Madame Bovary (Recommended for French speakers)");
        System.out.println("2. Victor Hugo - Les Miserables (Recommended for French speakers)");
        System.out.println("3. Jane Austen - Pride and Prejudice (Recommended for English speakers)");
        System.out.print("\nYour choice: ");
        
        int choice = scanner.nextInt();
        String[] files;
        int n = 2; 

        // Gestion des chemins de fichiers selon tes dossiers
        if (choice == 1) {
            files = new String[35];
            for (int i = 0; i < 35; i++) {
                files[i] = "bovary/" + String.format("%02d", i + 1) + ".txt";
            }
        } else if (choice == 2) {
            files = new String[5]; 
            for (int i = 0; i < 5; i++) {
                files[i] = "miserables/t" + (i + 1) + ".txt";
            }
        } else {
            files = new String[]{"austen/pride-prejudice.txt"};
            n = 4;
        }

        try {
            HMap table = buildTable(files, n);
            System.out.println("\n--- GENERATED TEXT ---\n");
            generate(table, n);
        } catch (Exception e) {
            System.out.println("Error: Could not load the files. Ensure the folders are in your project.");
        }
        
        scanner.close();
    }

    // Construction de la table avec ta méthode read()
    static HMap buildTable(String[] files, int n) {
        HMap table = new HMap();
        for (String f : files) {
            WordReader reader = new WordReader(f);
            Prefix p = new Prefix(n);
            
            String w = reader.read();
            while (w != null) {
                table.add(p, w);
                p = p.addShift(w);
                w = reader.read();
            }
        }
        return table;
    }

    // Fonction de génération avec tabulations et mise en page propre
    static void generate(HMap table, int n) {
        Prefix p = new Prefix(n);
        int lineLength = 80; // Largeur de ligne pour simuler une page
        StringBuilder currentLine = new StringBuilder("\t"); // Première tabulation
        
        while (true) {
            WordList l = table.find(p);
            if (l == null || l.content == null) break;
            String w = l.getRandom();
            if (w.equals("<END>")) break;
            
            if (w.equals("<PAR>")) {
                // Affiche la ligne en cours et saute un paragraphe
                System.out.println(currentLine.toString());
                System.out.print("\n\t"); // Saut de ligne + Tabulation pour le nouveau paragraphe
                currentLine = new StringBuilder(); 
            } else {
                // Vérifie si le mot rentre sur la ligne actuelle
                if (currentLine.length() + w.length() + 1 < lineLength) {
                    currentLine.append(w).append(" ");
                } else {
                    // Ligne pleine : on l'affiche et on passe à la suivante
                    System.out.println(currentLine.toString());
                    currentLine = new StringBuilder(w + " ");
                }
            }
            p = p.addShift(w);
        }
        // Affiche le dernier morceau de texte
        System.out.println(currentLine.toString());
    }
}
