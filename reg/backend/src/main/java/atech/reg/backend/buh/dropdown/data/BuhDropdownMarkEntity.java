package atech.reg.backend.buh.dropdown.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buh_dropdown_mark")
public class BuhDropdownMarkEntity {

    @Id
    public Long id;
    @Column(length = 50, nullable = false, unique = true)
    public String text;

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
