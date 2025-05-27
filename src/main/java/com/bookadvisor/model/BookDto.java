package com.bookadvisor.model;

/*
 * The box that contains the book information (DTO - Data Transfer Object)
 * This class is used to transfer data between the client and server.
 */
public class BookDto {
    private String title;
    private String author;
    private String coverUrl;
    private String publishDate;
    private String key;
    private String description;

    
    /**
     * Constructor for BookDto  
     *  
     * @param title
     * @param author
     * @param coverUrl
     * @param publishDate
     * @param key
     */
    public BookDto(String title, String author, String coverUrl, String publishDate, String description) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.publishDate = publishDate;
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getCoverUrl() {
        return coverUrl;
    }
    
    public String getPublishDate() {
        return publishDate;
    }
    
    public String getKey() {
        return key;
    }
    public String getDescription() {
          return description;
      }
    
      public void setDescription(String description) {
          this.description = description;
      }
}
