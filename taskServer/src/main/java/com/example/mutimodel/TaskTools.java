package com.example.mutimodel;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskTools {

    private final List<String> tasks = new ArrayList<>();

    @McpTool(name = "createTask", description = "Create a new task")
    public String createTask(
            @McpToolParam(
                    description = "Title of the task",
                    required = true
            ) String title) {
        System.out.println("Hello1");
        tasks.add(title);
        return "Task created: " + title;
    }

    @McpTool(name = "listTask", description = "Get all tasks")
    public List<String> listTask() {
        return List.copyOf(tasks);
    }

    @McpTool(name = "completeTask", description = "Complete a task by title")
    public String completeTask(
            @McpToolParam(
                    description = "Title of the task to complete",
                    required = true
            ) String title) {

        boolean removed = tasks.remove(title);
        System.out.println("Hello");
        if (removed) {
            return "Task completed: " + title;
        }
        System.out.println("Hello4");
        return "Task not found: " + title;
    }
}