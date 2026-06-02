# Task Tracker CLI

A command-line task management application built in Java that allows users to create, organize, track, and manage tasks directly from the terminal.

This project was developed to practice:

* Object-Oriented Programming (OOP)
* File manipulation with Java NIO
* Manual JSON serialization/deserialization
* Command Line Interface (CLI) development
* Layered architecture
* Error handling
* Collections and Streams API

---

# Features

## Task Management

* Add tasks
* Update tasks
* Delete tasks
* Mark tasks as:

  * TODO
  * IN_PROGRESS
  * DONE

## Listing

* List all tasks
* List tasks by status

## Search

* Search tasks by keyword

## Sorting

* Sort by creation date
* Sort by priority

## Statistics

* Total tasks
* TODO count
* IN_PROGRESS count
* DONE count
* Completion percentage

## Reports

* Export task reports to TXT

## Audit Log

Every operation is automatically recorded in:

```text
data/history.log
```

---

# Project Structure

```text
task-tracker/
│
├── src/
│   ├── app/
│   │   └── TaskTracker.java
│   │
│   ├── model/
│   │   ├── Task.java
│   │   ├── TaskStatus.java
│   │   └── Priority.java
│   │
│   ├── repository/
│   │   ├── TaskRepository.java
│   │   └── HistoryRepository.java
│   │
│   ├── service/
│   │   └── TaskService.java
│   │
│   └── util/
│       ├── JsonUtil.java
│       ├── DateUtil.java
│       └── ConsoleTable.java
│
├── data/
│   ├── tasks.json
│   └── history.log
│
└── README.md
```

---

# Task Model

Each task contains:

| Property    | Description             |
| ----------- | ----------------------- |
| id          | Unique identifier       |
| description | Task description        |
| status      | TODO, IN_PROGRESS, DONE |
| priority    | LOW, MEDIUM, HIGH       |
| createdAt   | Creation date           |
| updatedAt   | Last update date        |

Example:

```json
{
  "id": 1,
  "description": "Study Java",
  "status": "TODO",
  "priority": "HIGH",
  "createdAt": "2026-06-02 15:30:00",
  "updatedAt": "2026-06-02 15:30:00"
}
```

---

# Compilation

## Windows PowerShell

```powershell
javac -d bin `
src/model/*.java `
src/util/*.java `
src/repository/*.java `
src/service/*.java `
src/app/*.java
```

## Linux / macOS

```bash
javac -d bin \
src/model/*.java \
src/util/*.java \
src/repository/*.java \
src/service/*.java \
src/app/*.java
```

---

# Running the Application

```bash
java -cp bin app.TaskTracker
```

---

# Commands

## Add Task

```bash
java -cp bin app.TaskTracker add "Study Java" HIGH
```

Output:

```text
Task added successfully (ID: 1)
```

---

## Update Task

```bash
java -cp bin app.TaskTracker update 1 "Study Spring Boot"
```

---

## Delete Task

```bash
java -cp bin app.TaskTracker delete 1
```

---

## Mark Task as Done

```bash
java -cp bin app.TaskTracker mark-done 1
```

---

## Mark Task as In Progress

```bash
java -cp bin app.TaskTracker mark-in-progress 1
```

---

## Mark Task as TODO

```bash
java -cp bin app.TaskTracker mark-todo 1
```

---

## List All Tasks

```bash
java -cp bin app.TaskTracker list
```

---

## List Tasks by Status

### Done

```bash
java -cp bin app.TaskTracker list done
```

### TODO

```bash
java -cp bin app.TaskTracker list todo
```

### In Progress

```bash
java -cp bin app.TaskTracker list in-progress
```

---

## Search Tasks

```bash
java -cp bin app.TaskTracker search java
```

---

## Sort Tasks

### By Date

```bash
java -cp bin app.TaskTracker sort date
```

### By Priority

```bash
java -cp bin app.TaskTracker sort priority
```

---

## Statistics

```bash
java -cp bin app.TaskTracker stats
```

Example:

```text
========== STATS ==========
Total: 10
TODO: 4
IN_PROGRESS: 2
DONE: 4
Completion Rate: 40.00%
```

---

## Export Report

```bash
java -cp bin app.TaskTracker export
```

Generated file:

```text
data/tasks-report.txt
```

---

## Help

```bash
java -cp bin app.TaskTracker help
```

---

# Storage

Tasks are persisted in:

```text
data/tasks.json
```

Example:

```json
[
  {
    "id": 1,
    "description": "Study Java",
    "status": "DONE",
    "priority": "HIGH",
    "createdAt": "2026-06-02 15:00:00",
    "updatedAt": "2026-06-02 16:00:00"
  }
]
```

---

# Audit Log

Every action is recorded in:

```text
data/history.log
```

Example:

```text
[2026-06-02 15:10:01] ADD Task #1
[2026-06-02 15:15:23] UPDATE Task #1
[2026-06-02 15:18:12] DONE Task #1
```

---

# Technologies

* Java 25
* Java NIO
* Streams API
* Collections Framework
* CLI Architecture
* Manual JSON Parsing

No external dependencies or frameworks are used.

---

# Future Improvements

* Maven
* JUnit 5 tests
* Docker support
* GitHub Actions CI/CD
* Better JSON parser
* Pagination
* Task categories
* Due dates
* Tags
* CSV export
* REST API version
* Spring Boot integration

---

# License

This project is available for educational and portfolio purposes.

---

## Project URL

This project is based on the Task Tracker challenge from roadmap.sh:

Project URL: https://roadmap.sh/projects/task-tracker

The goal of the challenge is to build a command-line task management application that stores data in a JSON file and supports task creation, updates, deletion, status management, and filtering.