package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class ProgressTimerAndListener implements ActionListener {
    private JProgressBar _progressBar;
    private Timer _timer;

    public ProgressTimerAndListener(JProgressBar progressBar) {
        _progressBar = progressBar;
        _timer = new Timer(100, this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        DateInfo curDateInfo = new DateInfo();
        _progressBar.setValue((int)(1000000 * curDateInfo.getFractionOfLevelComplete()));
        _progressBar.setString("Level " + curDateInfo.getLevel());
        if (_timer.getDelay() != curDateInfo.getInterval()) {
            _timer.setDelay(curDateInfo.getInterval());
        }
    }

    public void start() {
        _timer.start();
    }
}