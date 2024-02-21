package atech.reg.backend.buh.dropdown.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buh_dropdown_about")
public class BuhDropdownAboutEntity {

    @Id
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
