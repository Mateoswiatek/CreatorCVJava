import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Parahraph{

    UnorederedList unorederedList = new UnorederedList();

    public ParagraphWithList(){
        super("list");
    }

    public ParagraphWithList(String title) {
        super(title);
    }

    public ParagraphWithList setContent(String consent){
        this.content = consent;
        return this;
    }

    public ParagraphWithList addListItem(String content){
        unorederedList.addItem(content);
        return this;
    }

    public void writeHTML(PrintStream out) {
        super.writeHTML(out);
        unorederedList.writeHTML(out);
    }
}
