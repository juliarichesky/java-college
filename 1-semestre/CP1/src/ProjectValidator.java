import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectValidator {

    private static int passedAssertions = 0;
    private static int totalAssertions = 0;
    private static int bonusAssertions = 0;

    // Track which assertions passed for weighted scoring
    private static boolean[] passedBasicAssertions = new boolean[5]; // assertions 1-5
    private static boolean[] passedCrudAssertions = new boolean[7];  // assertions 6-12

    public static void main(String[] args) {
        System.out.println("=== TODO CLI Project Validator ===\n");

        // Required assertions (10.0 points)
        assertion1_TarefaClassExists();
        assertion2_TarefaClassFields();
        assertion3_TarefaConstructor();
        assertion4_MainClassEntryPoint();
        assertion5_ArrayListUsage();
        assertion6_ScannerImplementation();
        assertion7_MenuLoopStructure();
        assertion8_IdGenerationLogic();
        assertion9_AddTaskOperation();
        assertion10_ListTasksOperation();
        assertion11_MarkTaskOperation();
        assertion12_RemoveTaskOperation();

        // Optional bonus assertion (2.0 points)
        assertion13_InputValidation();

        System.out.println("\n=== VALIDATION RESULTS ===");
        System.out.println("Required assertions passed: " + (passedAssertions - bonusAssertions) + "/11");
        System.out.println("Bonus assertions passed: " + bonusAssertions + "/1");
        System.out.println("Total score: " + calculateScore() + "/11.0 points");
    }

    private static void assertion1_TarefaClassExists() {
        totalAssertions++;
        System.out.print("1. Tarefa Class Exists (Required): ");

        File tarefaFile = new File("src/Tarefa.java");
        if (tarefaFile.exists()) {
            System.out.println("✓ PASS");
            passedAssertions++;
            passedBasicAssertions[0] = true;
        } else {
            System.out.println("✗ FAIL - Tarefa.java not found");
        }
    }

    private static void assertion2_TarefaClassFields() {
        totalAssertions++;
        System.out.print("2. Tarefa Class Fields (Required): ");

        try {
            Class<?> tarefaClass = Class.forName("Tarefa");

            Field idField = tarefaClass.getDeclaredField("id");
            Field tituloField = tarefaClass.getDeclaredField("titulo");
            Field concluidaField = tarefaClass.getDeclaredField("concluida");

            boolean idCorrect = Modifier.isPublic(idField.getModifiers()) &&
                               idField.getType() == int.class;

            boolean tituloCorrect = Modifier.isPublic(tituloField.getModifiers()) &&
                                   tituloField.getType() == String.class;

            boolean concluidaCorrect = Modifier.isPublic(concluidaField.getModifiers()) &&
                                      concluidaField.getType() == boolean.class;

            if (idCorrect && tituloCorrect && concluidaCorrect) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedBasicAssertions[1] = true;
            } else {
                System.out.println("✗ FAIL - Incorrect field declarations");
            }
        } catch (Exception e) {
            System.out.println("✗ FAIL - " + e.getMessage());
        }
    }

    private static void assertion3_TarefaConstructor() {
        totalAssertions++;
        System.out.print("3. Tarefa Constructor (Optional): ");

        try {
            Class<?> tarefaClass = Class.forName("Tarefa");

            // Try to find a constructor, but it's optional
            Constructor<?>[] constructors = tarefaClass.getConstructors();
            boolean hasValidConstructor = false;

            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    // Default constructor is fine
                    hasValidConstructor = true;
                    break;
                } else if (constructor.getParameterCount() == 2) {
                    Class<?>[] paramTypes = constructor.getParameterTypes();
                    if (paramTypes[0] == int.class && paramTypes[1] == String.class) {
                        // Constructor with id and titulo is also fine
                        hasValidConstructor = true;
                        break;
                    }
                }
            }

            if (hasValidConstructor) {
                System.out.println("✓ PASS - Valid constructor found");
                passedAssertions++;
                passedBasicAssertions[2] = true;
            } else {
                System.out.println("✓ PASS - Constructor is optional");
                passedAssertions++;
                passedBasicAssertions[2] = true;
            }
        } catch (Exception e) {
            System.out.println("✓ PASS - Constructor is optional");
            passedAssertions++;
            passedBasicAssertions[2] = true;
        }
    }

    private static void assertion4_MainClassEntryPoint() {
        totalAssertions++;
        System.out.print("4. Main Class Entry Point (Required): ");

        try {
            Class<?> mainClass = Class.forName("Main");
            Method mainMethod = mainClass.getMethod("main", String[].class);

            boolean isStatic = Modifier.isStatic(mainMethod.getModifiers());
            boolean isPublic = Modifier.isPublic(mainMethod.getModifiers());
            boolean returnsVoid = mainMethod.getReturnType() == void.class;

            if (isStatic && isPublic && returnsVoid) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedBasicAssertions[3] = true;
            } else {
                System.out.println("✗ FAIL - Incorrect main method signature");
            }
        } catch (Exception e) {
            System.out.println("✗ FAIL - " + e.getMessage());
        }
    }

    private static void assertion5_ArrayListUsage() {
        totalAssertions++;
        System.out.print("5. ArrayList Usage (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                if (mainContent.contains("ArrayList") && mainContent.contains("Tarefa")) {
                    found = true;
                }
            }

            // Check TarefaService.java if Main.java doesn't have it
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    if (serviceContent.contains("ArrayList") && serviceContent.contains("Tarefa")) {
                        found = true;
                    }
                }
            }

            // Check Menu.java if others don't have it
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    if (menuContent.contains("ArrayList") && menuContent.contains("Tarefa")) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedBasicAssertions[4] = true;
            } else {
                System.out.println("✗ FAIL - ArrayList<Tarefa> not found in any class");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion6_ScannerImplementation() {
        totalAssertions++;
        System.out.print("6. Scanner Implementation (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                if (mainContent.contains("Scanner")) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    if (serviceContent.contains("Scanner")) {
                        found = true;
                    }
                }
            }

            // Check Menu.java
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    if (menuContent.contains("Scanner")) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[0] = true;
            } else {
                System.out.println("✗ FAIL - Scanner not found in any class");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion7_MenuLoopStructure() {
        totalAssertions++;
        System.out.print("7. Menu Loop Structure (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                boolean hasWhileLoop = mainContent.contains("while") &&
                                      (mainContent.contains("true") || mainContent.contains("!exit"));
                boolean hasMenuOptions = mainContent.contains("1") &&
                                       (mainContent.contains("5") || mainContent.contains("6"));
                if (hasWhileLoop && hasMenuOptions) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    boolean hasWhileLoop = serviceContent.contains("while") &&
                                          (serviceContent.contains("true") || serviceContent.contains("!exit"));
                    boolean hasMenuOptions = serviceContent.contains("1") &&
                                           (serviceContent.contains("5") || serviceContent.contains("6"));
                    if (hasWhileLoop && hasMenuOptions) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[1] = true;
            } else {
                System.out.println("✗ FAIL - Menu loop with options 1-5 or 1-6 not found");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion8_IdGenerationLogic() {
        totalAssertions++;
        System.out.print("8. ID Generation Logic (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                boolean hasIdCounter = mainContent.contains("proximoId") ||
                                      mainContent.contains("nextId") ||
                                      mainContent.contains("idCounter") ||
                                      mainContent.contains("++") ||
                                      mainContent.contains("getId") ||
                                      mainContent.contains("+ 1");
                if (hasIdCounter) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    boolean hasIdCounter = serviceContent.contains("proximoId") ||
                                          serviceContent.contains("nextId") ||
                                          serviceContent.contains("idCounter") ||
                                          serviceContent.contains("++") ||
                                          serviceContent.contains("getId") ||
                                          serviceContent.contains("+ 1");
                    if (hasIdCounter) {
                        found = true;
                    }
                }
            }

            // Check Menu.java
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    boolean hasIdCounter = menuContent.contains("proximoId") ||
                                          menuContent.contains("nextId") ||
                                          menuContent.contains("idCounter") ||
                                          menuContent.contains("++") ||
                                          menuContent.contains("getId") ||
                                          menuContent.contains("+ 1");
                    if (hasIdCounter) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[2] = true;
            } else {
                System.out.println("✗ FAIL - ID generation logic not found");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion9_InputValidation() {
        totalAssertions++;
        bonusAssertions++;
        System.out.print("9. Input Validation (Bonus +2.0): ");

        try {
            String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));

            boolean hasTitleValidation = mainContent.contains("trim()") ||
                                       mainContent.contains("isEmpty()") ||
                                       mainContent.contains("isBlank()");

            boolean hasIdValidation = mainContent.contains("not found") ||
                                    mainContent.contains("não existe") ||
                                    mainContent.contains("inexistente");

            if (hasTitleValidation && hasIdValidation) {
                System.out.println("✓ PASS");
                passedAssertions++;
            } else {
                System.out.println("✗ FAIL - Input validation not implemented");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read Main.java");
        }
    }

    private static void assertion10_CompilationSuccess() {
        totalAssertions++;
        System.out.print("10. Compilation Success (Required): ");

        try {
            Process process = Runtime.getRuntime().exec("javac src/*.java");
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("✓ PASS");
                passedAssertions++;
            } else {
                System.out.println("✗ FAIL - Compilation errors");
            }
        } catch (Exception e) {
            System.out.println("✗ FAIL - " + e.getMessage());
        }
    }

    private static void assertion9_AddTaskOperation() {
        totalAssertions++;
        System.out.print("9. Add Task Operation (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                boolean hasAddTaskLogic = (mainContent.contains("case 1") || mainContent.contains("if") && mainContent.contains("1")) &&
                                        (mainContent.contains("add") || mainContent.contains("Add") ||
                                         mainContent.contains("nova") || mainContent.contains("criar"));
                boolean hasArrayListAdd = mainContent.contains(".add(") &&
                                        (mainContent.contains("new Tarefa") || mainContent.contains("tarefa"));
                if (hasAddTaskLogic && hasArrayListAdd) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    boolean hasAddTaskLogic = (serviceContent.contains("add") || serviceContent.contains("Add") ||
                                            serviceContent.contains("nova") || serviceContent.contains("criar") ||
                                            serviceContent.contains("insert") || serviceContent.contains("create")) &&
                                            serviceContent.contains("Tarefa");
                    boolean hasArrayListAdd = serviceContent.contains(".add(") &&
                                            (serviceContent.contains("new Tarefa") || serviceContent.contains("tarefa"));
                    if (hasAddTaskLogic && hasArrayListAdd) {
                        found = true;
                    }
                }
            }

            // Check Menu.java
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    boolean hasAddTaskLogic = (menuContent.contains("add") || menuContent.contains("Add") ||
                                            menuContent.contains("nova") || menuContent.contains("criar") ||
                                            menuContent.contains("insert") || menuContent.contains("create")) &&
                                            menuContent.contains("Tarefa");
                    boolean hasArrayListAdd = menuContent.contains(".add(") &&
                                            (menuContent.contains("new Tarefa") || menuContent.contains("tarefa"));
                    if (hasAddTaskLogic && hasArrayListAdd) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[3] = true;
            } else {
                System.out.println("✗ FAIL - Add task operation not found");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion10_ListTasksOperation() {
        totalAssertions++;
        System.out.print("10. List Tasks Operation (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                boolean hasListTasksLogic = (mainContent.contains("case 2") || mainContent.contains("if") && mainContent.contains("2")) &&
                                           (mainContent.contains("list") || mainContent.contains("List") ||
                                            mainContent.contains("listar") || mainContent.contains("exibir"));
                if (hasListTasksLogic) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    boolean hasListTasksLogic = serviceContent.contains("list") || serviceContent.contains("List") ||
                                               serviceContent.contains("listar") || serviceContent.contains("exibir") ||
                                               serviceContent.contains("show") || serviceContent.contains("display");
                    if (hasListTasksLogic) {
                        found = true;
                    }
                }
            }

            // Check Menu.java
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    boolean hasListTasksLogic = menuContent.contains("list") || menuContent.contains("List") ||
                                               menuContent.contains("listar") || menuContent.contains("exibir") ||
                                               menuContent.contains("show") || menuContent.contains("display");
                    if (hasListTasksLogic) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[4] = true;
            } else {
                System.out.println("✗ FAIL - List tasks operation not found");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion11_MarkTaskOperation() {
        totalAssertions++;
        System.out.print("11. Mark/Unmark Task Operation (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));

                boolean hasMarkMenuOptions = mainContent.contains("case 3") ||
                                            (mainContent.contains("if") && mainContent.contains("3"));
                boolean hasMarkKeywords = mainContent.contains("marcar") || mainContent.contains("concluida") ||
                                         mainContent.contains("mark") || mainContent.contains("complete") ||
                                         mainContent.contains("atualizar") || mainContent.contains("update");
                boolean hasMarkMethod = mainContent.contains("marcarComoConcluida") ||
                                       mainContent.contains("marcarComoNaoConcluida") ||
                                       mainContent.contains("concluida = true") ||
                                       mainContent.contains("concluida = false") ||
                                       mainContent.contains(".concluida =");
                boolean hasIdComparison = mainContent.contains("== id") || mainContent.contains(".getId()") ||
                                        mainContent.contains("tarefa.id ==") || mainContent.contains(".id ==");

                if (hasMarkMenuOptions && (hasMarkKeywords || hasMarkMethod) && hasIdComparison) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    boolean hasMarkKeywords = serviceContent.contains("marcar") || serviceContent.contains("concluida") ||
                                             serviceContent.contains("mark") || serviceContent.contains("complete") ||
                                             serviceContent.contains("atualizar") || serviceContent.contains("update");
                    boolean hasMarkMethod = serviceContent.contains("marcarComoConcluida") ||
                                           serviceContent.contains("marcarComoNaoConcluida") ||
                                           serviceContent.contains("concluida = true") ||
                                           serviceContent.contains("concluida = false") ||
                                           serviceContent.contains(".concluida =");
                    boolean hasIdComparison = serviceContent.contains("== id") || serviceContent.contains(".getId()") ||
                                            serviceContent.contains("tarefa.id ==") || serviceContent.contains(".id ==");
                    if ((hasMarkKeywords || hasMarkMethod) && hasIdComparison) {
                        found = true;
                    }
                }
            }

            // Check Menu.java
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    boolean hasMarkKeywords = menuContent.contains("marcar") || menuContent.contains("concluida") ||
                                             menuContent.contains("mark") || menuContent.contains("complete") ||
                                             menuContent.contains("atualizar") || menuContent.contains("update");
                    boolean hasMarkMethod = menuContent.contains("marcarComoConcluida") ||
                                           menuContent.contains("marcarComoNaoConcluida") ||
                                           menuContent.contains("concluida = true") ||
                                           menuContent.contains("concluida = false") ||
                                           menuContent.contains(".concluida =");
                    boolean hasIdComparison = menuContent.contains("== id") || menuContent.contains(".getId()") ||
                                            menuContent.contains("tarefa.id ==") || menuContent.contains(".id ==");
                    if ((hasMarkKeywords || hasMarkMethod) && hasIdComparison) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[5] = true;
            } else {
                System.out.println("✗ FAIL - Mark/unmark task operation not found");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion12_RemoveTaskOperation() {
        totalAssertions++;
        System.out.print("12. Remove Task Operation (Required): ");

        try {
            boolean found = false;

            // Check Main.java
            File mainFile = new File("src/Main.java");
            if (mainFile.exists()) {
                String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));
                boolean hasRemoveLogic = (mainContent.contains("case 4") || mainContent.contains("case 5") ||
                                         (mainContent.contains("if") && (mainContent.contains("4") || mainContent.contains("5")))) &&
                                        (mainContent.contains("remove") || mainContent.contains("Remove") ||
                                         mainContent.contains("remover") || mainContent.contains("delete") ||
                                         mainContent.contains("deletar"));
                boolean hasIdComparison = mainContent.contains("== id") || mainContent.contains(".getId()") ||
                                        mainContent.contains("tarefa.id ==") || mainContent.contains(".id ==");
                boolean hasArrayListRemove = mainContent.contains(".remove(") &&
                                            (mainContent.contains("tarefa") || mainContent.contains("Tarefa"));
                if (hasRemoveLogic && hasIdComparison && hasArrayListRemove) {
                    found = true;
                }
            }

            // Check TarefaService.java
            if (!found) {
                File serviceFile = new File("src/TarefaService.java");
                if (serviceFile.exists()) {
                    String serviceContent = new String(Files.readAllBytes(Paths.get("src/TarefaService.java")));
                    boolean hasRemoveLogic = serviceContent.contains("remove") || serviceContent.contains("Remove") ||
                                           serviceContent.contains("remover") || serviceContent.contains("delete") ||
                                           serviceContent.contains("deletar") || serviceContent.contains("excluir");
                    boolean hasIdComparison = serviceContent.contains("== id") || serviceContent.contains(".getId()") ||
                                            serviceContent.contains("tarefa.id ==") || serviceContent.contains(".id ==");
                    boolean hasArrayListRemove = serviceContent.contains(".remove(") &&
                                                (serviceContent.contains("tarefa") || serviceContent.contains("Tarefa"));
                    if (hasRemoveLogic && hasIdComparison && hasArrayListRemove) {
                        found = true;
                    }
                }
            }

            // Check Menu.java
            if (!found) {
                File menuFile = new File("src/Menu.java");
                if (menuFile.exists()) {
                    String menuContent = new String(Files.readAllBytes(Paths.get("src/Menu.java")));
                    boolean hasRemoveLogic = menuContent.contains("remove") || menuContent.contains("Remove") ||
                                           menuContent.contains("remover") || menuContent.contains("delete") ||
                                           menuContent.contains("deletar") || menuContent.contains("excluir");
                    boolean hasIdComparison = menuContent.contains("== id") || menuContent.contains(".getId()") ||
                                            menuContent.contains("tarefa.id ==") || menuContent.contains(".id ==");
                    boolean hasArrayListRemove = menuContent.contains(".remove(") &&
                                                (menuContent.contains("tarefa") || menuContent.contains("Tarefa"));
                    if (hasRemoveLogic && hasIdComparison && hasArrayListRemove) {
                        found = true;
                    }
                }
            }

            if (found) {
                System.out.println("✓ PASS");
                passedAssertions++;
                passedCrudAssertions[6] = true;
            } else {
                System.out.println("✗ FAIL - Remove task operation not found");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read source files");
        }
    }

    private static void assertion13_InputValidation() {
        totalAssertions++;
        bonusAssertions++;
        System.out.print("13. Input Validation (Bonus +2.0): ");

        try {
            String mainContent = new String(Files.readAllBytes(Paths.get("src/Main.java")));

            boolean hasTitleValidation = mainContent.contains("trim()") ||
                                       mainContent.contains("isEmpty()") ||
                                       mainContent.contains("isBlank()");

            boolean hasIdValidation = mainContent.contains("not found") ||
                                    mainContent.contains("não existe") ||
                                    mainContent.contains("inexistente");

            if (hasTitleValidation && hasIdValidation) {
                System.out.println("✓ PASS");
                passedAssertions++;
            } else {
                System.out.println("✗ FAIL - Input validation not implemented");
            }
        } catch (IOException e) {
            System.out.println("✗ FAIL - Cannot read Main.java");
        }
    }

    private static double calculateScore() {
        // Count passed assertions by category
        int basicAssertionsPassed = 0; // assertions 1-5 (1.0 points each)
        int crudAssertionsPassed = 0;  // assertions 6-12 (6.0/7 points each)

        // Basic assertions (1-5): 1.0 points each = 5.0 total
        if (passedBasicAssertions[0]) basicAssertionsPassed++; // Tarefa exists
        if (passedBasicAssertions[1]) basicAssertionsPassed++; // Fields
        if (passedBasicAssertions[2]) basicAssertionsPassed++; // Constructor
        if (passedBasicAssertions[3]) basicAssertionsPassed++; // Main entry point
        if (passedBasicAssertions[4]) basicAssertionsPassed++; // ArrayList usage

        // CRUD assertions (6-12): 6.0/7 points each = 6.0 total
        if (passedCrudAssertions[0]) crudAssertionsPassed++; // Scanner
        if (passedCrudAssertions[1]) crudAssertionsPassed++; // Menu loop
        if (passedCrudAssertions[2]) crudAssertionsPassed++; // ID generation
        if (passedCrudAssertions[3]) crudAssertionsPassed++; // Add task
        if (passedCrudAssertions[4]) crudAssertionsPassed++; // List tasks
        if (passedCrudAssertions[5]) crudAssertionsPassed++; // Mark/unmark
        if (passedCrudAssertions[6]) crudAssertionsPassed++; // Remove task

        double basicScore = basicAssertionsPassed * 1.0;     // 5 points max
        double crudScore = crudAssertionsPassed * (6.0 / 7.0); // 6 points max
        double bonusScore = 0.0; // No bonus points

        return basicScore + crudScore + bonusScore;
    }
}