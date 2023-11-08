import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w Szczecinie")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w Krakowie")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania")
                                .addListItem("ROS2")
                                .addListItem("Java")
                                .addListItem("Python dla leniwych")
                                .addListItem("STM32")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                )
                .addParagraph(
                        new ParagraphWithList().setContent("Posiadane Kontakty")
                                .addListItem("Prezydent RP")
                                .addListItem("Szef NASA")
                                .addListItem("Dziekan AGH")
                );

        cv.writeHTML(System.out);
        cv.writeHTML(new PrintStream("cv.html","UTF-8"));
    }
}