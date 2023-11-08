import java.io.PrintStream;

public class Photo {

    String url;

    public Photo(String url){
        this.url = url;
    }

    public void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"420\" width=\"420\"/>\n",url);
    }
}