
public class EntryList {
    Entry head;      // L'entrée stockée dans ce maillon
    EntryList next;  // Lien vers la collision suivante (chaînage)

    // Constructeur d'un maillon de la liste de collisions
    EntryList(Entry head, EntryList next) {
        this.head = head;
        this.next = next;
    }
}