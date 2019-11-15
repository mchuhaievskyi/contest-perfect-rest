package ua.kpi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Folder {

    @Id
    @GeneratedValue
    private Long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> documents;

    public void addDocument(Document document) {
        if (documents == null) {
            documents = new ArrayList<>();
        }
        documents.add(document);
    }

    public void deleteDocument(Document document) {
        if (documents == null) {
            throw new EntityNotFoundException();
        }
        documents.remove(document);
    }
}
