
package monitor.utils;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class ProgressMonitor {
    
    private long maxValue;
    private long initValue;
    private long currentValue;
    private double progress;
    private boolean estimating = false;
    
    private String progressMessage;

    public ProgressMonitor(){
        clear();
    }

    public ProgressMonitor(long maxValue, long initValue) {
        this.maxValue = maxValue;
        this.initValue = initValue;
        currentValue = initValue;
        progress = 0.d;
    }

    public void next(){
        currentValue++;
        calcProgress();
    }
    
    public void next(long increment){
        currentValue += increment;
    }
    
    public void clear(){
        maxValue = 0l;
        initValue = 0l;
        currentValue = 0l;
        progress = 0.d;
    }
    
    private void calcProgress(){
        if(maxValue != 0){
            progress = (currentValue * 100)/maxValue;
        }
    }
    
    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public long getInitValue() {
        return initValue;
    }

    public void setInitValue(long initValue) {
        this.initValue = initValue;
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;
    }

    public double getProgress() {
        calcProgress();
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getProgressMessage() {
        return progressMessage;
    }

    public void setProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
    }

    public boolean isEstimating() {
        return estimating;
    }

    public void setEstimating(boolean estimating) {
        this.estimating = estimating;
    }
    
}
