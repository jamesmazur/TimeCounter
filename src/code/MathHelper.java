package code;

public class MathHelper {
    public static long myExp2(long inputValue) {
        long retValue = 1;
        while (inputValue > 0) {
            retValue *= 2;
            --inputValue;
        }
        return retValue;
    }

    public static long myLog2(long inputValue) {
        long retValue = 0;
        while (inputValue > 0) {
            inputValue /= 2;
            ++retValue;
        }
        return retValue - 1;
    }
}
