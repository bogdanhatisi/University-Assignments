package org.example.seminar1.runners;

import org.example.seminar1.models.Task;

/**
 * DelayTaskRunner - delays each task by a set amount of time
 */
public class DelayTaskRunner extends AbstractTaskRunner{
    public DelayTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }

    @Override
    public void executeOneTask() {
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        taskRunner.executeOneTask();

    }

}
