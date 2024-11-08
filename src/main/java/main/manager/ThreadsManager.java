package main.manager;

import javax.swing.SwingUtilities;

import main.view.pannels.ExercisePannel;


public class ThreadsManager extends Thread {

    private boolean stopped = true;
    private ExercisePannel exercisePannel;
    private long programStart = System.currentTimeMillis();
    private long pauseStart = programStart;
    private long pauseCount = 0;

    public ThreadsManager(String name, ExercisePannel exercisePannel) {
        super(name);
        this.exercisePannel = exercisePannel;
        setDaemon(true);  
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!stopped) {
                long elapsed = System.currentTimeMillis() - programStart - pauseCount;
                SwingUtilities.invokeLater(() -> {
                    exercisePannel.loadTime(format(elapsed));  
                });
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

  
    private String format(long elapsed) {  
        int hour, minute, second, milli;  

        milli = (int) (elapsed % 1000);  
        elapsed = elapsed / 1000;  

        second = (int) (elapsed % 60);  
        elapsed = elapsed / 60;  

        minute = (int) (elapsed % 60);  
        elapsed = elapsed / 60;  

        hour = (int) (elapsed % 60);  

        return String.format("%02d:%02d:%02d %03d", hour, minute, second, milli);  
    }  


   
    public void resumeTimer() {
        stopped = false;  
        programStart = System.currentTimeMillis() - pauseCount; 
    }

    
    public void stopTimer() {
    	pauseStart = programStart;  
        pauseCount = 0;
        stopped = true;
        exercisePannel.resetTimerFiled();
    }
    public void pauseTime() {
    	if(stopped = true) {
    		   pauseCount += (System.currentTimeMillis() - pauseStart);  
               stopped = false; 
               exercisePannel.changeButtonText();
    	}else {
    		 pauseStart = System.currentTimeMillis();  
             stopped = true;  
             exercisePannel.changeButtonTextTo();
    	}
    }
}
