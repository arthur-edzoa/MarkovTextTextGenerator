
public class Prefix {
    String[] t; // Tableau stockant la séquence de mots (le contexte)

    // Constantes pour les balises de structure
    final static String start = "<START>", end = "<END>", par = "<PAR>";
    
    // Constructeur : initialise un préfixe vide rempli de balises <START>
    Prefix(int n){
        this.t = new String[n];
        for(int i = 0; i < n; i++) {
            this.t[i] = start;
        }
    }

    // Compare deux préfixes (Indispensable pour retrouver une clé dans la HMap)
    public static boolean eq(Prefix p1, Prefix p2) {
        if(p1.t.length != p2.t.length) return false;
        for(int i = 0; i < p1.t.length; i++) {
            // Comparaison du contenu des tableaux mot par mot
            if (!p1.t[i].equals(p2.t[i])) return false;
        }
        return true;
    }

    // Algorithme de "fenêtre glissante" : décale les mots et ajoute le nouveau à la fin
    Prefix addShift(String motSuivant) {
        Prefix nouveauPrefixe = new Prefix(this.t.length);
        for(int i = 0; i < this.t.length - 1; i++) {
            nouveauPrefixe.t[i] = this.t[i + 1]; // Décalage vers la gauche
        }
        if(this.t.length > 0) {
            nouveauPrefixe.t[this.t.length - 1] = motSuivant; // Insertion du nouveau mot
        }
        return nouveauPrefixe;
    }

    // Fonction de hachage : transforme un objet complexe en un indice numérique
    public int hashCode(int tailleTable) {
        int h = 0;
        for (int i = 0; i < this.t.length; i++) {
            // Utilise le hashCode de String combiné à un multiplicateur premier (37)
            // pour minimiser les collisions
            h = 37 * h + t[i].hashCode();
        }
        // Utilisation du modulo pour rester dans les bornes du tableau
        return Math.abs(h % tailleTable);
    }
}