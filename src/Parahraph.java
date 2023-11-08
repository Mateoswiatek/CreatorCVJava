import java.io.PrintStream;

public class Parahraph {

    String content;

    public Parahraph(String title){
        this.setContent(title);
    }

    public Parahraph setContent(String content){
        this.content = content;
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>%n", content);
    }
}
