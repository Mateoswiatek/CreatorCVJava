import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {

    String title;

    List<Parahraph> parahraphList = new ArrayList<>();

    public Section(String title){
        this.title = title;
    }

    public Section setTitle(String title){
        this.title = title;
        return this;
    }

    public Section addParagraph(String paragraphTitle){
        this.parahraphList.add(new Parahraph(paragraphTitle));
        return this;
    }

    public Section addParagraph(Parahraph p){
        this.parahraphList.add(p);
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<h1>%s</h1>%n", title);
        for (Parahraph p : parahraphList) {
            p.writeHTML(out);
        }
    }
}
