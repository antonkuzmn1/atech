package atech.reg.backend.session.user;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import atech.reg.backend.session.group.GroupEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "session_user")
public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty("id")
        private Long id;

        @Column(length = 15, nullable = false, unique = true)
        @JsonProperty("login")
        private String login;

        @Column(length = 15, nullable = false)
        @JsonProperty("password")
        private String password;

        @Column(length = 50, nullable = false)
        @JsonProperty("name")
        private String name;

        @Column(length = 255, nullable = false,
                columnDefinition = "varchar(255) default ''")
        @JsonProperty("description")
        private String description;

        @Column(nullable = false,
                columnDefinition = "tinyint default 0")
        @JsonProperty("deleted")
        private boolean deleted;

        @ManyToMany
        @JsonIgnoreProperties("users")
        @JoinTable(name = "session_m2m_user_group",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "group_id"))
        @JsonProperty("groups")
        private Set<GroupEntity> groups = new HashSet<>();

        public Long getId() { return id; }
        public String getLogin() { return login; }
        public String getPassword() { return password; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public boolean getDeleted() { return deleted; }
        public Set<GroupEntity> getGroups() { return groups; }

        public void setLogin(String login) { this.login = login; }
        public void setPassword(String password) { this.password = password; }
        public void setName(String name) { this.name = name; }
        public void setDescription(String description) { this.description = description; }
        public void setDeleted(boolean deleted) { this.deleted = deleted; }
        public void setGroups(Set<GroupEntity> groups) { this.groups = groups; }

}