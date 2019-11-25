package ua.kpi.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Folder> folders;

    public void addFolder(Folder folder) {
        if (folders == null) {
            folders = new ArrayList<>();
        }
        folders.add(folder);
    }

    public void deleteFolder(Folder folder) {
        if (folders == null) {
            throw new EntityNotFoundException();
        }
        folders.remove(folder);
    }
}
