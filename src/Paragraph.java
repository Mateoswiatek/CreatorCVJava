import java.io.PrintStream;

public class Paragraph {

    String content;

    public Paragraph(String title){
        this.setContent(title);
    }

    public Paragraph setContent(String content){
        this.content = content;
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>%n", content);
    }
}