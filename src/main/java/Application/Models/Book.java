package Application.Models;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, message = "Name must be longer than 3 characters")
    private String name;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private Integer numberOfCopies;

    @NotNull
    private Category category;

//    private MultipartFile img;

    public Book() {}

    public Book(Long id, String name, Integer numberOfCopies, Category category//, MultipartFile img
    ) {
        this.id = id;
        this.name = name;
        this.numberOfCopies = numberOfCopies;
        this.category = category;
        // this.img = img;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getNumberOfCopies() { return numberOfCopies; }
    public void setNumberOfCopies(Integer numberOfCopies) { this.numberOfCopies = numberOfCopies; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
//    public String getImage() throws IOException {
//        return img.getOriginalFilename();
//    }
//    public void setImage(String img) throws IOException {
//        File file = new File(img);
//        FileInputStream input = new FileInputStream(file);
//        String contentType = "image/jpg";
//        this.img = new MockMultipartFile(file.getName(),
//                file.getName(), contentType, IOUtils.toByteArray(input));
//    }
}
