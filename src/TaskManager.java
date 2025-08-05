import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskServiceImpl service = new TaskServiceImpl();

        while (true) {
            System.out.println("\n========= TASK MANAGEMENT =========");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Display task");
            System.out.println("4. Exit");

            int option;
            try {
                System.out.print("Your choice: ");
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 4.");
                continue;
            }

            switch (option) {
                case 1 -> {
                    String requirementName = inputRequirementName(sc);
                    int typeId = inputTaskTypeId(sc);
                    String dateStr = inputDate(sc);

                    double from, to;
                    while (true) {
                        try {
                            System.out.print("From (8.0 - 17.0): ");
                            from = inputDouble(sc);
                            System.out.print("To (8.5 - 17.5): ");
                            to = inputDouble(sc);
                            TaskValidator.validateTime(from, to);
                            break;
                        } catch (Exception e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }

                    String assignee = inputName(sc, "Assignee");
                    String reviewer = inputName(sc, "Reviewer");

                    int id = service.addTask(requirementName, assignee, reviewer,
                            String.valueOf(typeId), dateStr, String.valueOf(from), String.valueOf(to));
                    System.out.println("=> Add task with ID: " + id);
                }

                case 2 -> {
                    System.out.print("Enter the ID to delete: ");
                    String idStr = sc.nextLine();
                    service.deleteTask(idStr);
                    System.out.println("=> Deleted successfully!");
                }

                case 3 -> {
                    System.out.println("ID   Requirement     Type       Date         Time       Assignee   Reviewer");
                    for (Task t : service.getDataTasks()) {
                        System.out.println(t.toRowString());
                    }
                }

                case 4 -> {
                    System.out.println("Exit program.");
                    return;
                }

                default -> System.out.println("Wrong selection! Please select 1-4.");
            }
        }
    }

    // ✅ Hàm nhập Requirement Name (phải có chữ, không rỗng)
    private static String inputRequirementName(Scanner sc) {
        String name;
        while (true) {
            System.out.print("Requirement Name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Requirement Name cannot be empty!");
            } else if (!name.matches(".*[a-zA-Z].*")) {
                System.out.println("Requirement Name must contain at least one letter!");
            } else {
                return name;
            }
        }
    }

    // ✅ Hàm nhập Task Type ID (1-4)
    private static int inputTaskTypeId(Scanner sc) {
        int typeId;
        while (true) {
            try {
                System.out.print("TaskTypeID (1-Code, 2-Test, 3-Design, 4-Review): ");
                typeId = Integer.parseInt(sc.nextLine());
                if (typeId >= 1 && typeId <= 4) return typeId;
                System.out.println("TaskTypeID must be from 1 to 4!");
            } catch (NumberFormatException e) {
                System.out.println("Integer required!");
            }
        }
    }

    // ✅ Hàm nhập Date đúng định dạng
    private static String inputDate(Scanner sc) {
        while (true) {
            try {
                System.out.print("Date (dd-MM-yyyy): ");
                String dateStr = sc.nextLine();
                Task.parseDate(dateStr); // kiểm tra hợp lệ
                return dateStr;
            } catch (Exception e) {
                System.out.println("Invalid Date/Month! VD: 01-08-2025");
            }
        }
    }

    // ✅ Hàm nhập số thực
    private static Double inputDouble(Scanner sc) {
        while (true) {
            try {
                System.out.print("Enter number: ");
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Real numbers must be entered. VD: 8.5");
            }
        }
    }

    // ✅ Hàm nhập Assignee / Reviewer (phải chứa chữ)
    private static String inputName(Scanner sc, String label) {
        String name;
        while (true) {
            System.out.print(label + ": ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(label + " không được để trống!");
            } else if (!name.matches(".*[a-zA-Z].*")) {
                System.out.println(label + " phải chứa ít nhất một ký tự chữ!");
            } else {
                return name;
            }
        }
    }
}
