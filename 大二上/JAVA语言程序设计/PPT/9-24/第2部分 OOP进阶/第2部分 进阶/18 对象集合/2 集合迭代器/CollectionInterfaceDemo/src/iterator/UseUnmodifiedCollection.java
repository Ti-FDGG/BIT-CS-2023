package iterator;

import java.util.ArrayList;
import java.util.Collections;

public class UseUnmodifiedCollection {
    public static void main(String[] args) {
        var books = new ArrayList<String>();
        books.add("One book");
        books.add("Two book");
        books.add("Three book");
        var readonlyBooks =
                Collections.unmodifiableCollection(books);
        var iterator = readonlyBooks.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }
}
