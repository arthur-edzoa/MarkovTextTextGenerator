
public class Node {
    String head;
    Node next;
    
    Node(String head, Node next){
        this.head = head;
        this.next = next;
    }
    
    // Instance de test originale
    static Node foobar = new Node("foo", new Node("bar", new Node("baz", null)));

    // --- FONCTIONS UTILES AU PROJET ---

    static int length(Node liste) {
        if (liste == null) return 0;
        int compteur = 0;
        for (Node actuel = liste; actuel != null; actuel = actuel.next) {
            compteur++;
        }
        return compteur;
    }

    static String makeString(Node liste) {
        String resultat = "[";
        Node actuel = liste;
        while(actuel != null) {
            resultat += actuel.head;
            if (actuel.next != null) resultat += ", ";
            actuel = actuel.next;
        }
        return resultat + "]";
    }

    static void addLast(String mot, Node liste) {
        Node actuel = liste;
        while(actuel.next != null) {
            actuel = actuel.next;
        }
        actuel.next = new Node(mot, null);
    }

    // --- APPLICATIONS ALGORITHMIQUES ---

    static int lengthRec(Node liste) {
        if (liste == null) return 0;
        return lengthRec(liste.next) + 1;
    }

    static Node insertionSort(Node liste) {
        if (liste == null) return null;
        Node listeTriee = null;
        Node actuel = liste;
        while(actuel != null) {
            listeTriee = insert(actuel.head, listeTriee);
            actuel = actuel.next;
        }
        return listeTriee;
    }

    static Node insert(String mot, Node liste) {
        if (liste == null || mot.compareTo(liste.head) <= 0) {
            return new Node(mot, liste);
        }
        Node actuel = liste;
        while (actuel.next != null && mot.compareTo(actuel.next.head) >= 0) {
            actuel = actuel.next;
        }
        actuel.next = new Node(mot, actuel.next);
        return liste;
    }

    static Node copy(Node liste) {
        if (liste == null) return null;
        Node nouvelleTete = new Node(liste.head, null);
        Node curseurNouveau = nouvelleTete;
        Node curseurAncien = liste.next;
        while(curseurAncien != null) {
            curseurNouveau.next = new Node(curseurAncien.head, null);
            curseurNouveau = curseurNouveau.next;
            curseurAncien = curseurAncien.next;
        }
        return nouvelleTete;
    }

    public static Node merge(Node liste1, Node liste2) {
        if(liste1 == null) return liste2;
        if(liste2 == null) return liste1;
        Node resultat;
        if(liste1.head.compareTo(liste2.head) <= 0) {
            resultat = liste1;
            resultat.next = merge(liste1.next, liste2);
        } else {
            resultat = liste2;
            resultat.next = merge(liste1, liste2.next);
        }
        return resultat;
    }
}