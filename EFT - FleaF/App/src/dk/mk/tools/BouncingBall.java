package dk.mk.tools;

public class BouncingBall {

    public static int bouncingBall(double h, double bounce, double window) {
        if(h <= 0)
            return -1;
        if(bounce <= 0 || bounce >= 1)
            return -1;
        if(window >= h)
            return -1;

        int seenTimes = 1;
        h = bounceOnce(h, bounce);

        while(h > window){
            h = bounceOnce(h, bounce);
            seenTimes += 2;
        }

        return seenTimes;
    }

    public static double bounceOnce(double h, double bounce){
        return h * bounce;
    }
}