import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskServiceImpl service = new TaskServiceImpl();

        while (true) {
            System.out.println("\n========= TASK MANAGEMENT =========");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Display  task");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            String option = sc.nextLine();

            try {
                switch (option) {
                    case "1" -> {
                        System.out.print("Requirement Name: ");
                        String requirementName = sc.nextLine();

                        int typeId;
                        while (true) {
                            try {
                                System.out.print("TaskTypeID (1-Code, 2-Test, 3-Design, 4-Review): ");
                                typeId = Integer.parseInt(sc.nextLine());
                                if (typeId >= 1 && typeId <= 4) break;
                                System.out.println("TaskTypeID must be from 1 to 4!");
                            } catch (NumberFormatException e) {
                                System.out.println("Integer required!");
                            }
                        }

                        String dateStr;
                        while (true) {
                            try {
                                System.out.print("Date (dd-MM-yyyy): ");
                                dateStr = sc.nextLine();
                                Task.parseDate(dateStr); // kiểm tra ngay
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid Date/Month! VD: 01-08-2025");
                            }
                        }

                        double from, to;
                        while (true) {
                            try {
                                System.out.print("From (8.0 - 17.0): ");
                                from = Double.parseDouble(sc.nextLine());
                                System.out.print("To (8.5 - 17.5): ");
                                to = Double.parseDouble(sc.nextLine());
                                TaskValidator.validateTime(from, to); // kiểm tra ngay
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Real numbers must be entered. VD: 8.5");
                            } catch (Exception e) {
                                System.out.println("Lỗi giờ: " + e.getMessage());
                            }
                        }

                        System.out.print("Assignee: ");
                        String assignee = sc.nextLine();

                        System.out.print("Reviewer: ");
                        String reviewer = sc.nextLine();

                        int id = service.addTask(requirementName, assignee, reviewer,
                                String.valueOf(typeId), dateStr, String.valueOf(from), String.valueOf(to));
                        System.out.println("=> Add task with ID: " + id);
                    }
                    case "2" -> {
                        System.out.print("Enter the ID to delete: ");
                        String idStr = sc.nextLine();
                        service.deleteTask(idStr);
                        System.out.println("=> Deleted successfully!");
                    }
                    case "3" -> {
                        System.out.println("ID   Requirement     Type       Date         Time       Assignee   Reviewer");
                        for (Task t : service.getDataTasks()) {
                            System.out.println(t.toRowString());
                        }
                    }
                    case "4" -> {
                        System.out.println("Exit program.");
                        return;
                    }
                    default -> System.out.println("Wrong selection! Please select 1-4.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}