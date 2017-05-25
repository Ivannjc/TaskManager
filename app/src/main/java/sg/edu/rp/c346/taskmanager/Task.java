package sg.edu.rp.c346.taskmanager;

import java.io.Serializable;

/**
 * Created by 15017608 on 25/5/2017.
 */

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + name + "\n" + description;
    }
}
