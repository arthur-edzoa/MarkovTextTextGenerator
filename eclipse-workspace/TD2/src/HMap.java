
public class HMap {
    EntryList[] buckets; // Tableau d'alvéoles pour le hachage
    int nbEntries;       // Nombre total de préfixes stockés
    
    // Initialise la table avec une taille n (réduit les collisions si n est grand)
    HMap(int n){
        this.buckets = new EntryList[n];
        this.nbEntries = 0;
    }
    
    HMap() { this(20); }

    // Calcule la profondeur d'une alvéole (pour monitorer l'efficacité du hachage)
    int length(EntryList liste) {
        if(liste == null || liste.head == null) return 0;
        int cpt = 0;
        for(EntryList cur = liste; cur != null; cur = cur.next) {
            cpt++;
        }
        return cpt;
    }

    // Recherche un préfixe : O(1) en moyenne, O(n) dans le pire cas de collision
    WordList find(Prefix cle) {
        int indice = cle.hashCode(this.buckets.length);
        EntryList actuel = this.buckets[indice];
        while(actuel != null) {
            if(Prefix.eq(cle, actuel.head.key)) return actuel.head.value;
            actuel = actuel.next;
        }
        return null;
    }
    
    // Ajoute un mot à un préfixe existant ou crée une nouvelle entrée
    void addSimple(Prefix cle, String mot) {
        WordList listeExistante = find(cle);
        if (listeExistante != null) {
            listeExistante.addLast(mot);
        } else {
            int indice = cle.hashCode(this.buckets.length);
            WordList nouvelleListe = new WordList();
            nouvelleListe.addLast(mot);
            // Insertion en tête de liste de collision
            this.buckets[indice] = new EntryList(new Entry(cle, nouvelleListe), this.buckets[indice]);
            this.nbEntries++;
        }
    }

    void add(Prefix cle, String mot) { addSimple(cle, mot); }
}