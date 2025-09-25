# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java console-based TODO List application developed as part of a basic Java assessment. The project implements a simple in-memory task management system without external dependencies or persistence.

## Architecture

- **Simple Java Console Application**: No frameworks, pure Java with Scanner for input
- **In-Memory Storage**: Uses `ArrayList<Tarefa>` for task storage (no persistence)
- **Two-Class Structure**:
  - `Tarefa.java`: Task model with public fields (`id`, `titulo`, `concluida`)
  - `Main.java`: Console application with menu-driven interface

## Development Commands

### Compilation and Execution
```bash
# Compile all Java files
javac src/*.java

# Run the application (from project root)
java -cp src Main

# Alternative compilation from src directory
cd src
javac *.java
java Main
```

### Project Structure
```
TodoCLI/
├── src/
│   ├── Main.java      # Main application with menu loop
│   └── Tarefa.java    # Task model (to be implemented)
├── Enunciado.md       # Project requirements (Portuguese)
└── TodoCLI.iml        # IntelliJ IDEA module file
```

## Implementation Requirements

### Task Model (`Tarefa.java`)
- Must use public fields (no getters/setters):
  - `public final int id`
  - `public String titulo`
  - `public boolean concluida`
- Constructor accepting `id` and `titulo`
- Optional utility methods: `marcarConcluida()`, `desmarcarConcluida()`

### Main Application Features
1. Add task (with incremental ID generation)
2. List tasks (format: `[X]/[ ] id - titulo`)
3. Mark/unmark task as completed
4. Remove task
5. Exit application

### Input Validation
- Non-empty task titles (trim whitespace)
- Valid task IDs for operations
- Protection against invalid menu selections
- Handle Scanner input edge cases (nextInt() followed by nextLine())

## Development Notes

- **No External Dependencies**: Console-only application using standard Java
- **No Persistence**: Tasks are lost when application exits
- **ID Management**: Use incremental counter (`int proximoId = 1`)
- **Error Handling**: Provide clear success/error messages to users
- **IntelliJ Project**: Configured as standard Java module with `src` source folder