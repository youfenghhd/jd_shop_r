package com.example.demo.utils.thread;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;

public class CircularTask {
    private List<TaskProcess> childTasks;
    public void initLoopTask() {
        childTasks = new ArrayList<>();
        childTasks.add(new TaskProcess("Task"));
//        childTasks.add(new ChildTask("Task1"));
        for (final TaskProcess childTask : childTasks) {
            new Thread(childTask::doExecute).start();
        }
    }
    public void shutLoopTask() {
        if (!CollectionUtils.isEmpty(childTasks)) {
            for (TaskProcess childTask : childTasks) {
                childTask.terminal();
            }
        }
    }
    public static void main(String[] args) throws Exception{
        CircularTask loopTask = new CircularTask();
        loopTask.initLoopTask();
        Thread.sleep(5*1000);
        loopTask.shutLoopTask();
    }
}