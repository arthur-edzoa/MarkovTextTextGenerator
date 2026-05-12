
public class Entry {
    Prefix key;      // Le contexte (la clé)
    WordList value;  // Les successeurs possibles (la valeur)

    // Constructeur simple liant la clé à sa liste de mots
    Entry (Prefix key, WordList value) {
        this.key = key;
        this.value = value;
    }
}