

import java.util.List;

public class Particle {

    double mass;
    Vector position;
    Vector velocity;
    Vector force;


    public Vector socialForce(List<Particle> influence){
        return new Vector(0,0);
    }

    public Vector desireForce(Vector taget){
        return new Vector(0,0);
    }
}
