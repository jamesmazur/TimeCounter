package code;

import java.util.Date;

public class DateInfo {
    private double _fractionComplete;
    private int _interval;
    private int _level;

    public DateInfo() {
        Date curDate = new Date();
        long timePassed = curDate.getTime() - Driver.startDate.getTime();
        if (timePassed > 0) {
            _level = (int)(MathHelper.myLog2(timePassed / 1000 + 1)) + 1;
            long levelLength = MathHelper.myExp2(_level - 1) * 1000;
            long timeInThisLevel = timePassed - levelLength + 1000;
            _fractionComplete = ((double)timeInThisLevel) / levelLength;
            if (_level < 4) {
                _interval = 100;
            } else {
                _interval = (int)(100 * MathHelper.myExp2(_level - 4));
                _interval = _interval >= 300000 ? 300000 : _interval;
            }
        } else {
            _fractionComplete = ((double)(curDate.getTime() - Driver.programLaunchDate.getTime()))
                    / (Driver.startDate.getTime() - Driver.programLaunchDate.getTime());
            _interval = 100;
            _level = 0;
        }
    }

    public double getFractionOfLevelComplete() {
        return _fractionComplete;
    }

    public int getInterval() {
        return _interval;
    }

    public int getLevel() {
        return _level;
    }
}