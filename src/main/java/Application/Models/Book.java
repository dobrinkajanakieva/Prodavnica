package Application.Models;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.expression.AccessException;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.net.URI;
import java.net.URL;

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

    private MultipartFile image;
    private String imageBase64;

    public Book() {}

    public Book(Long id, String name, Integer numberOfCopies, Category category, MultipartFile image
    ) throws IOException {
        this.id = id;
        this.name = name;
        this.numberOfCopies = numberOfCopies;
        this.category = category;
        this.image = image;
        byte[] bytes = image.getBytes();
        this.imageBase64 = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getNumberOfCopies() { return numberOfCopies; }
    public void setNumberOfCopies(Integer numberOfCopies) { this.numberOfCopies = numberOfCopies; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public MultipartFile getImage() { return image; }
    public String getImageBase64() throws IOException {
        if (image == null) return "";
        return imageBase64;
    }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }
    public void setImage(MultipartFile img) { this.image = img; }
}
