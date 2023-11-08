import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    private Document document;

    private ByteArrayOutputStream outputStream;

    private String testTitle;

    @BeforeEach
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
        section.addParagraph("This is a test paragraph.");

        document.writeHTML(new PrintStream(outputStream));
        String result = outputStream.toString();

        // Testing whether the resulting HTML file contains exactly the specified elements
        assertTrue(result.contains("<title>Test Document</title>"));
        assertTrue(result.contains("<img src=\"https://example.com/photo.jpg\" alt=\"Smiley face\" height=\"420\" width=\"420\"/>"));
        assertTrue(result.contains("<h1>Test Section</h1>"));
        assertTrue(result.contains("<p>This is a test paragraph.</p>"));

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

    @Test
    public void testToJson() {
        Document document = new Document("Test Document");
        document.setPhoto("test-photo-url")
                .addSection("Section 1")
                .addParagraph("Paragraph 1")
                .addParagraph("Paragraph 2");
        document.addSection("Subsection 1")
                .addParagraph(
                        new ParagraphWithList().setContent("List Title")
                            .addListItem("Item 1")
                            .addListItem("Item 2")
                );
        document.addSection("Section 2")
                .addParagraph("Paragraph 3");

        String jsonString = document.toJson();

        assertNotNull(jsonString);
        assertTrue(jsonString.contains("Test Document"));
        assertTrue(jsonString.contains("test-photo-url"));
        assertTrue(jsonString.contains("Section 1"));
        assertTrue(jsonString.contains("Section 2"));
        assertTrue(jsonString.contains("Subsection 1"));
        assertTrue(jsonString.contains("Paragraph 1"));
        assertTrue(jsonString.contains("Paragraph 2"));
        assertTrue(jsonString.contains("Paragraph 3"));
        assertTrue(jsonString.contains("List Title"));
        assertTrue(jsonString.contains("Item 1"));
        assertTrue(jsonString.contains("Item 2"));
    }

    @Test
    public void testFromJson() {

        document.setTitle(testTitle);
        document.setPhoto("https://example.com/photo.jpg");
        Section section = document.addSection("Test Section");
        section.addParagraph("This is a test paragraph.");
        document.writeHTML(new PrintStream(outputStream));
        String jsonString = document.toJson(); // nie powinno tak sie robic raczej ale jesli jest przetestowane ze dziala poprawnie.


        Document document = new Document("tytul").fromJson(jsonString);

        assertNotNull(document);
        assertEquals(testTitle, document.title);
        assertNotNull(document.photo);
        assertFalse(document.sectionList.isEmpty());
        assertFalse(document.sectionList.getFirst().ParagraphList.isEmpty()); // jest paragraf
    }
}