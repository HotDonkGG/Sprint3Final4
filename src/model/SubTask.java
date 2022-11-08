package model;

public class SubTask extends Task {
    private Epic epic;

    public SubTask(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epic=" + epic +
                '}';
    }
}