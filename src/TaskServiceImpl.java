import java.util.*;

public class TaskServiceImpl {
    private final List<Task> taskList = new ArrayList<>();

    public int nextId() {
        return taskList.isEmpty() ? 1 : taskList.get(taskList.size() - 1).getId() + 1;
    }

    public int addTask(String requirementName, String assignee, String reviewer,
                       String taskTypeID, String date, String planFrom, String planTo) throws Exception {
        int typeId = Integer.parseInt(taskTypeID);
        double from = Double.parseDouble(planFrom);
        double to = Double.parseDouble(planTo);
        TaskValidator.validate(typeId, from, to);
        Date d = Task.parseDate(date);
        Task task = new Task(nextId(), typeId, requirementName, d, from, to, assignee, reviewer);
        taskList.add(task);
        return task.getId();
    }

    public void deleteTask(String idStr) throws Exception {
        int id = Integer.parseInt(idStr);
        Task found = findById(id);
        if (found == null) throw new Exception("Task ID not found: " + id);
        taskList.remove(found);
    }

    public Task findById(int id) {
        for (Task t : taskList) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public List<Task> getDataTasks() {
        return taskList;
    }
}
