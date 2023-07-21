package org.us.famulei.model;

import javax.persistence.*;

@Entity
@Table(name = "carriers")
public class Carrier {

        public Carrier() {}
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private long id;
        @Column(name = "name")
        private String name;
        @Column(name = "description")
        private String description;
        @Column(name = "location")
        private String location;

        public void setId(long id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public void setLocation(String location) {
                this.location = location;
        }
}
