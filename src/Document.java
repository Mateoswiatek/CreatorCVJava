import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;


public class Document {

    String title;

    Photo photo;

    List<Section> sectionList = new ArrayList<>();

    public Document(String title){
        this.title = title;
    }

    public Document setTitle(String title){
        this.title = title;
        return this;
    }

    public Document setPhoto(String photourl){
        this.photo = new Photo(photourl);
        return this;
    }

    public Section addSection(String sectionTitle){
        Section section = new Section(sectionTitle);
        this.sectionList.add(section);
        return section;
    }

    public Document addSection(Section s){
        this.sectionList.add(s);
        return this;
    }

    public void writeHTML(PrintStream out){
        out.printf("""
                <!DOCTYPE html>
                <html>
                <head>
                  <title>%s</title>
                </head>
                <body>\n""", title);

        photo.writeHTML(out);
        for (Section s: sectionList) {
            s.writeHTML(out);
        }

        out.println("""
                </body>
                </html>""");
    }

    String toJson() {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public Document fromJson(String jsonString) {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
        return gson.fromJson(jsonString, Document.class);
    }

}