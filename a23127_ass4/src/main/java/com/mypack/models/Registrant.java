package com.mypack.models;

import jakarta.validation.constraints.*;

public class Registrant {

    private Long id;

    @NotBlank(message = "{fullName.required}")
    @Size(min = 3, max = 60, message = "{fullName.size}")
    @Pattern(regexp = "^[\\p{L} .'-]{3,60}$", message = "{fullName.pattern}")
    private String fullName;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.format}")
    @Size(max = 120, message = "{email.size}")
    private String email;

    @NotBlank(message = "{workshop.required}")
    @Pattern(regexp = "^(AI in Practice|Cloud Computing|Cybersecurity Basics)$",
             message = "{workshop.invalid}")
    private String workshopTitle;

    public Registrant() {}
    public Registrant(Long id, String fullName, String email, String workshopTitle) {
        this.id = id; this.fullName = fullName; this.email = email; this.workshopTitle = workshopTitle;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWorkshopTitle() { return workshopTitle; }
    public void setWorkshopTitle(String workshopTitle) { this.workshopTitle = workshopTitle; }
}
