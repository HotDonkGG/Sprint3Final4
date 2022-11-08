import manager.Manager;
import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task1 = manager.addTask(new Task("Тест1", "Добавляем первый тест", TaskStatus.NEW));

        Task task44 = new Task("Тест44", "Тест44", TaskStatus.IN_PROGRESS);

        Epic epic1 = manager.addEpic(new Epic("Эпик1", "Добавляем тест1 эпик"));
        Epic epic2 = manager.addEpic(new Epic("Эпик2", "Добавляем тест2 эпик"));

        System.out.println(epic2.getId());

        SubTask subTask1 = manager.addSubTask(new SubTask("subtask", "text subtask2", TaskStatus.NEW));
        SubTask subTask2 = manager.addSubTask(new SubTask("subtask", "text subtask2", TaskStatus.NEW));
        SubTask subTask3 = manager.addSubTask(new SubTask("subtask", "text subtask2", TaskStatus.NEW));

        epic1.getSubTaskList().add(subTask1);
        epic1.getSubTaskList().add(subTask2);

        epic2.getSubTaskList().add(subTask3);

        subTask1.setEpic(epic1);
        subTask2.setEpic(epic2);

        Task task3 = manager.addTask(new Task("Тест23", "Тест апдейта 1", TaskStatus.NEW));

        task3.setName("raz");

        manager.updateTask(task3);

        System.out.println(manager.getTaskById(7L));
        manager.updateTask(task44);
        Task task2 = manager.addTask(new Task("Тест2", "Добавляем второй тест", TaskStatus.NEW));
        //  System.out.println(manager.getTaskById());
    }
}