import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {

    String title;

    List<Paragraph> ParagraphList = new ArrayList<>();

    public Section(String title){
        this.title = title;
    }

    public Section setTitle(String title){
        this.title = title;
        return this;
    }

    public Section addParagraph(String paragraphTitle){
        this.ParagraphList.add(new Paragraph(paragraphTitle));
        return this;
    }

    public Section addParagraph(Paragraph p){
        this.ParagraphList.add(p);
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<h1>%s</h1>%n", title);
        for (Paragraph p : ParagraphList) {
            p.writeHTML(out);
        }
    }
}