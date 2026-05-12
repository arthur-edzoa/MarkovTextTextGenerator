
public class WordList {
    Node content; // La tête de la liste chaînée

    // --- CONSTRUCTEURS ORIGINAUX ---

    // Constructeur vide
    WordList() { 
        this.content = null; 
    }
    
    // Constructeur à partir d'un noeud existant
    WordList(Node l) { 
        this.content = l; 
    }

    // Constructeur à partir d'un tableau de chaînes (vu dans ton original)
    WordList(String[] mots) {
        for (int i = 0; i < mots.length; i++) {
            this.addLast(mots[i]);
        }
    }

    // --- INSTANCES DE TEST (NE PAS SUPPRIMER) ---
    static WordList foobar = new WordList();
    static {
        foobar.content = new Node("foo", new Node("bar", new Node("baz", null)));
    }

    // --- MÉTHODES UTILES AU PROJET MARKOV ---

    // Renvoie la taille de la liste en appelant la méthode de Node
    int length() {
        return Node.length(this.content);
    }
    
    // Pour l'affichage dans la console
    public String toString() {
        return Node.makeString(this.content);
    }

    // Choisit un mot au hasard (essentiel pour la génération de texte)
    public String getRandom() {
        int taille = length();
        if (taille == 0) return "<END>";
        int indexAleatoire = (int)(Math.random() * taille);
        Node actuel = this.content;
        for (int i = 0; i < indexAleatoire; i++) {
            actuel = actuel.next;
        }
        return actuel.head;
    }

    // Ajoute un mot au tout début
    void addFirst(String mot) {
        this.content = new Node(mot, this.content);
    }
    
    // Ajoute un mot à la fin
    void addLast(String mot) {
        if (this.content == null) {
            this.content = new Node(mot, null);
        } else {
            Node.addLast(mot, this.content);
        }
    }

    // --- APPLICATIONS ALGORITHMIQUES (TRIS ET STRUCTURES) ---

    // Tri par insertion (In-place) - Version fidèle à ton original
    void insertionSort() {
        if (this.content == null) return;
        Node listeTriee = null;
        Node actuel = this.content;
        while(actuel != null) {
            // On utilise la logique d'insertion manuelle
            if (listeTriee == null || actuel.head.compareTo(listeTriee.head) < 0) {
                listeTriee = new Node(actuel.head, listeTriee);
            } else {
                Node curseurTri = listeTriee;
                while(curseurTri.next != null && actuel.head.compareTo(curseurTri.next.head) > 0) {
                    curseurTri = curseurTri.next;
                }
                curseurTri.next = new Node(actuel.head, curseurTri.next);
            }
            actuel = actuel.next;
        }
        this.content = listeTriee;
    }

    // Point d'entrée pour le tri fusion (Merge Sort)
    void mergeSort() {
        if (this.content == null || this.content.next == null) return;
        this.content = mergeSortRecursive(this.content);
    }

    // Logique récursive du tri fusion
    Node mergeSortRecursive(Node liste) {
        if(liste == null || liste.next == null) return liste;
        Node milieu = getMiddle(liste);
        Node suivantMilieu = milieu.next;
        milieu.next = null; // Coupe la liste en deux
        
        return Node.merge(mergeSortRecursive(liste), mergeSortRecursive(suivantMilieu));
    }

    // Trouve le milieu de la liste (Algorithme du lièvre et de la tortue)
    Node getMiddle(Node liste) {
        if (liste == null) return null;
        Node lent = liste;
        Node rapide = liste;
        while (rapide.next != null && rapide.next.next != null) {
            lent = lent.next;
            rapide = rapide.next.next;
        }
        return lent;
    }
}