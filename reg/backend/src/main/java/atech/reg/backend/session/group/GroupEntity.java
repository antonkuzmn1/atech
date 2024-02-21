package atech.reg.backend.session.group;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import atech.reg.backend.session.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "session_group")
public class GroupEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty("id")
        private Long id;

        @Column(length = 30, nullable = false, unique = true)
        @JsonProperty("name")
        private String name;

        @Column(length = 255, nullable = false, columnDefinition = "varchar(255) default ''")
        @JsonProperty("description")
        private String description;

        @Column(nullable = false, columnDefinition = "tinyint default 0")
        @JsonProperty("deleted")
        private boolean deleted;

        @ManyToMany(mappedBy = "groups")
        @JsonIgnoreProperties("groups")
        @JsonProperty("users")
        private Set<UserEntity> users = new HashSet<>();

        public GroupEntity() {
        }

        public GroupEntity(
                        String name,
                        String description) {
                this.name = name;
                this.description = description;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getDescription() {
                return description;
        }

        public boolean getDeleted() {
                return deleted;
        }

        public Set<UserEntity> getUsers() {
                return users;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public void setDeleted(boolean deleted) {
                this.deleted = deleted;
        }

        public void setUsers(Set<UserEntity> users) {
                this.users = users;
        }

}
