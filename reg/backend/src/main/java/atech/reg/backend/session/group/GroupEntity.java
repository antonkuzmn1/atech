package atech.reg.backend.session.group;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import atech.reg.backend.session.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 255, nullable = false,
            columnDefinition = "varchar(255) default ''")
    private String description;

    @Column(columnDefinition = "tinyint default 0")
    private boolean deleted;

    @ManyToMany(mappedBy = "groups")
    // @JsonManagedReference
    @JsonIgnoreProperties("groups")
    private Set<UserEntity> users = new HashSet<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean getDeleted() { return deleted; }
    public Set<UserEntity> getUsers() { return users; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
    public void setUsers(Set<UserEntity> users) { this.users = users; }

}
