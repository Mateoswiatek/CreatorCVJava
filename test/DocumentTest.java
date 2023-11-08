import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    private Document document;

    private ByteArrayOutputStream outputStream;

    private String testTitle;

    @BeforeAll
    void setUp() {
        document = new Document("Test Document");
        outputStream = new ByteArrayOutputStream();
        testTitle = "Aa1+-!;";
    }

    @Test
    void setTitle() {
        document.setTitle(testTitle);
        assertEquals(testTitle, document.title);
    }

    @Test
    void setPhoto() {
        document.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg",
                document.photo.url);
    }

    @Test
    void addSectionTitle() {
        assertEquals(0, document.sectionList.size());

        Section section = document.addSection(testTitle);

        assertEquals(testTitle, section.title);
        assertEquals(1, document.sectionList.size());
    }

    @Test
    void testAddSectionObject() {
        assertEquals(0, document.sectionList.size());

        Section section = new Section(testTitle);
        document.addSection(section);

        assertEquals(testTitle, section.title);
        assertEquals(1, document.sectionList.size());
    }

    @Test
    void writeHTML() {
        document.setTitle("Test Document");
        document.setPhoto("https://example.com/photo.jpg");
        Section section = document.addSection("Test Section");
        section.add("This is a test .");

        document.writeHTML(new PrintStream(outputStream));
        String result = outputStream.toString();

        // Testing whether the resulting HTML file contains exactly the specified elements
        assertTrue(result.contains("<title>Test Document</title>"));
        assertTrue(result.contains("<img src=\"https://example.com/photo.jpg\" alt=\"Smiley face\" height=\"420\" width=\"420\"/>"));
        assertTrue(result.contains("<h1>Test Section</h1>"));
        assertTrue(result.contains("<p>This is a test .</p>"));

        // More general tests, just tags
        assertTrue(result.contains("<title"));
        assertTrue(result.contains("/title>"));

        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));

        assertTrue(result.contains("<h1"));
        assertTrue(result.contains("/h1>"));

        assertTrue(result.contains("<p"));
        assertTrue(result.contains("/p>"));
    }
}