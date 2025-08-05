import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id;
    private int taskTypeId;
    private String requirementName;
    private Date date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String reviewer;

    public Task(int id, int taskTypeId, String requirementName, Date date,
                double planFrom, double planTo, String assignee, String reviewer) {
        this.id = id;
        this.taskTypeId = taskTypeId;
        this.requirementName = requirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    public int getId() {
        return id;
    }

    public static Date parseDate(String dateStr)  {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            return sdf.parse(dateStr);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

    }

    public String toRowString() {
        String[] taskTypes = { "Code", "Test", "Design", "Review"};
        String typeName = taskTypeId >= 1 && taskTypeId <= 4 ? taskTypes[taskTypeId] : "Unknown";
        return String.format("%-5d%-17s%-11s%-13s%-11.1f%-11s%-11s", id, requirementName, typeName,
                new SimpleDateFormat("dd-MM-yyyy").format(date), planTo - planFrom, assignee, reviewer);
    }
}