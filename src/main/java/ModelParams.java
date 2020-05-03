public class ModelParams {
    static int N = 200;
    static double X_max = 20;
    static double Y_max = 20;
    static double door_width = 1.2;
    static Vector door_pos = new Vector(0,X_max/2);
    static double r_min = 0.25;
    static double r_max = 0.29;
    static double v_min = 0.8;//m/s
    static double v_max = 6;//m/s
    static double A = 2000;//N
    static double B = 0.08;//m
    static double vdi = 0.5;//TODO:ver cuanto vale
    static double tau = 0.5;//s
    static double mass = 70;
    static Vector target1 = new Vector(X_max/2,0);
    static Vector target2 = new Vector(X_max/2,-5);
    static double dt= 0.1;//s
}
