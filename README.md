# CV Generator

## Introduction
In the modern world, having a well-structured and professional curriculum vitae (CV) is of paramount importance. A CV is not just a document; it's your ticket to new opportunities, your way to stand out in a competitive job market, and your first impression on potential employers. This project, the CV Generator, is designed to help you create compelling and customizable CVs easily.

## Key Features
The CV Generator is built on a set of Java classes that allow you to craft impressive CVs with multiple sections, paragraphs, and lists. Whether you're a student, a professional, or someone in-between, this project empowers you to present your skills, experiences, and achievements in a polished and organized format.

## Lessons Learned
While working on this project, I gained valuable insights into software development, including:

- **Object-Oriented Programming:** I learned the principles of object-oriented programming and how to design and implement classes and objects to model real-world concepts.

- **Serialization and Deserialization:** I mastered the art of serializing Java objects into JSON format and deserializing them back, which is essential for data interchange and storage.

- **Unit Testing:** I developed a comprehensive set of unit tests to ensure the correctness of my code, improving my confidence in its reliability.

- **Documenting Code:** I understood the importance of well-documented code, as evident in this `README.md`, which helps other developers and users understand the project.

- **Problem-Solving:** I encountered and resolved various challenges during the project, enhancing my problem-solving and critical-thinking skills.

## How to Use

The project provides a versatile set of classes to help you create and customize your CV. The primary class to interact with is `Document`, which serves as the foundation for crafting your CV. Below are descriptions of some of the main methods you can use:

#### Method `setTitle(String title)`
You can set the title of your CV using this method. It allows you to specify a meaningful and eye-catching title for your document.

### Method `setPhoto(String photourl)`
This method lets you add a photo to your CV. You can provide the URL of an image, which will be displayed at the top of your document.

### Method `addSection(String sectionTitle)`
With this method, you can create and add sections to your CV. Each section can have its title, and you can organize the content of your CV by adding paragraphs and lists to these sections.

### Method `addSection(Section s)`
If you already have a section created separately, you can add it to your CV using this method. This is useful if you want to assemble your CV from pre-defined sections.

### Method `writeHTML(PrintStream out)`
After you've built your CV, you can generate the HTML representation of the document using this method. It writes the HTML to the specified PrintStream.


### Method `toJson()`
This method serializes your CV into a JSON format, making it easy to store and share digitally. It's particularly useful if you want to save your CV for future reference.

### Method `fromJson(String jsonString)`
If you have a JSON representation of your CV, you can use this method to deserialize it back into a Document object. This feature is handy for editing or updating your CV.

These are some of the core methods that you can use to create, customize, and manipulate your CV using the `Document` class. Feel free to explore the other classes like `Section`, `Paragraph`, and `ParagraphWithList` for more advanced content structuring.

## Example
```java
public class Main {
    public static void main(String[] args) {
        // Create a new CV document
        Document cv = new Document("Jane Smith - CV");

        // Add a photo to the CV
        cv.setPhoto("https://example.com/jane-smith-photo.jpg");

        // Add an "Education" section
        Section educationSection = cv.addSection("Education");

        // Add paragraphs to the "Education" section
        educationSection.addParagraph("2005-2010 Bachelor's in Computer Science, University XYZ");
        educationSection.addParagraph("2010-2012 Master's in Software Engineering, University ABC");

        // Add a "Skills" section
        Section skillsSection = cv.addSection("Skills");

        // Add paragraphs with lists to the "Skills" section
        ParagraphWithList knownTechnologies = new ParagraphWithList().setContent("Known Technologies");
        knownTechnologies.addListItem("Java");
        knownTechnologies.addListItem("Python");
        knownTechnologies.addListItem("C++");
        skillsSection.addParagraph(knownTechnologies);

        ParagraphWithList contacts = new ParagraphWithList().setContent("Contacts");
        contacts.addListItem("LinkedIn: Jane Smith");
        contacts.addListItem("Email: jane.smith@example.com");
        skillsSection.addParagraph(contacts);

        // Generate HTML to cv.html file
        cv.writeHTML(new PrintStream("cv.html","UTF-8"));

        // Serialize to JSON
        String jsonString = cv.toJson();
        System.out.println(jsonString);

        // Deserialize from JSON
        Document document = new Document("New Title").fromJson(jsonString);

        // Generate HTML for the deserialized document
        document.writeHTML(System.out);
    }
}
```



## Author
This project was created by Mateusz Świątek.
