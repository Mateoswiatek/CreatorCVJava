import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorederedList {

    List<ListItem> itemList = new ArrayList<>();

    public UnorederedList addItem(String item){
        itemList.add(new ListItem(item));
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.println("<ul>");
        for (ListItem item : itemList) {
            item.writeHTML(out);
        }
        out.println("</ul>");
    }
}