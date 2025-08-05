public class TaskValidator {
    public static void validate(int typeId, double from, double to)  {
        if (typeId < 1 || typeId > 4)
            throw new RuntimeException("TaskTypeID phải từ 1 đến 4.");
        validateTime(from, to);
    }

    public static void validateTime(double from, double to) {
        if (from < 8.0 || from > 17.0)
            throw new RuntimeException("Start time must be between 8.0 and 17.0");
        if (to <= from)
            throw new RuntimeException("End time must be greater than start time.");
        if (to > 17.5)
            throw new RuntimeException("End time must not exceed 17.5");
    }
}
