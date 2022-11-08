package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    private Long taskMaxId = 0L;
    private final Map<Long, Epic> epics = new HashMap<>(); // Создаю мап для эпиков: ключ(id), значение(эпик)
    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, SubTask> subTasks = new HashMap<>();

    public List<Epic> getAllEpic() { //получаю все значения эпика в формате листа
        return (List<Epic>) epics.values();
    }

    public void clearEpic() { //удаляю все сабтаски и эпики
        clearSubtask();
        epics.clear();
    }

    public Epic getEpicById(Long id) { // возвращаю значение ключа эпика
        return epics.get(id);
    }

    public Epic addEpic(Epic epic) { //добавлению новый эпик в мапу и возвращаю добавленный эпик
        epic.setId(taskMaxId++);
        if (epic != null && !epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        }
        return epic;
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) { // проверят наличие эпика в мапе и если он есть, обноваляет его
            epics.put(epic.getId(), epic);
        }
    }

    public Epic removeEpicById(Long id) {
        for (SubTask subTask : epics.get(id).getSubTaskList()) {
            subTasks.remove(subTask.getId());
        }
        return epics.remove(id);
    }

    public List<SubTask> getAllSubTask() { // получаю все значения сабтаска в формате листа
        return (List<SubTask>) subTasks.values();
    }

    public void clearSubtask() { // очищаю все сабтаски
        for (Epic epic : epics.values()) {
            epic.getSubTaskList().clear();
            epic.setStatus(TaskStatus.NEW);
        }
        subTasks.clear();
    }

    public SubTask getSubTaskById(Long id) { //возвращаю значение ключа сабтаска
        return subTasks.get(id);
    }

    public SubTask addSubTask(SubTask subTask) {
        subTask.setId(taskMaxId++);//добавлению новый сабтаск в мапу и возвращаю добавленный сабтаск
        if (subTask != null && !subTasks.containsKey(subTask.getId())) {
            subTasks.put(subTask.getId(), subTask);
        }
        return subTask;
    }

    public SubTask removeSubTaskById(Long id) { //удаляю значения ключа сабтаска
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        if (epic.getSubTaskList().size() == 1) {
            epic.setStatus(TaskStatus.DONE); //если закончились сабтаски, значит эпик готов
        }
        epic.getSubTaskList().remove(subTask);
        return subTasks.remove(id);
    }

    public SubTask updateSubTask(SubTask subTask) {
        Epic epic = subTask.getEpic();
        boolean isDone = false;
        for (SubTask subTask1 : epic.getSubTaskList()) {
            if (!subTask1.getStatus().equals(TaskStatus.DONE)) {
                isDone = true;
                break;
            }
        }
        if (!isDone && subTask.getStatus().equals(TaskStatus.DONE)) {
            updateStatusEpic(epic, TaskStatus.DONE);
        }
        return subTasks.put(subTask.getId(), subTask);
    }

    public List<Task> getAllTask() {
        return (List<Task>) tasks.values();
    }

    public void clearTask() {
        tasks.clear();
    }

    public Task getTaskById(Long id) {
        return tasks.get(id);
    }

    public void updateStatusEpic(Epic epic, TaskStatus taskStatus) {
        epic.setStatus(taskStatus);
    }

    public Task addTask(Task task) {
        task.setId(taskMaxId++);//добавлению новый сабтаск в мапу и возвращаю добавленный сабтаск
        if (task != null && !tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
        return task;
    }

    public Task removeTaskById(Long id) {
        return tasks.remove(id);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public List<SubTask> getAllSubtaskByEpic(Epic epic) {
        return epic.getSubTaskList();
    }
}