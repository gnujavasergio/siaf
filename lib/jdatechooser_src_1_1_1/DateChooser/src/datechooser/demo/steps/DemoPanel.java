/*
 * DemoPanel.java
 *
 * Created on 15 ќкт€брь 2006 г., 14:24
 *
 */

package datechooser.demo.steps;

import javax.swing.JPanel;

/**
 *
 * @author Vadik
 */
public abstract class DemoPanel extends JPanel {
    
    private int currentStep = 0;
    private DemoPanelListener listener;
    
    public abstract int getStepCount();
    public abstract String getText();
    public abstract void play();
    
    public boolean hasNext() {
        return getCurrentStep() < (getStepCount() - 1); 
    }
    
    public boolean hasPrevious() {
        return getCurrentStep() > 0;
    }
    
    public void next() {
        setCurrentStep(getCurrentStep() + 1);
    }
    
    public void previous() {
        setCurrentStep(getCurrentStep() - 1);
    }
    
   public int getCurrentStep() {
        return currentStep;
    }

    private void setCurrentStep(int currentStep) {
        boolean changed = getCurrentStep() != currentStep;
        this.currentStep = currentStep;
        if (changed) {
            fireStep();
            play();
        }
    }

    public void setStepListener(DemoPanelListener listener) {
        this.listener = listener;
    }
    
    private void fireStep() {
        if (listener != null) listener.onStep();
    }
}
