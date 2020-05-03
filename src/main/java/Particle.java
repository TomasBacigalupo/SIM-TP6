

import java.util.List;

public class Particle {

    double radius;
    double mass;
    Vector position;
    Vector velocity;
    Vector force;


    public Vector socialForce(List<Particle> influence){
        Vector force = new Vector(0,0);
        double Eij = 0;
        Vector eij;
        for(Particle p : influence){

            //necesito la distancia a p y versor de direccion

            if(this != p){
                Eij = this.position.distance(p.position)-this.radius-p.radius;
                eij = this.position.nRest(p.position).multScalar(1/this.position.distance(p.position));
                double forceMod = ModelParams.A * Math.exp(-Eij/ModelParams.B);
                Vector forceVector = eij.multScalar(forceMod);
                force.sum(forceVector);
            }

        }

        return new Vector(0,0);
    }

    public Vector desireForce(Vector taget){
        return new Vector(0,0);
    }

















    public Particle(Vector position,Vector velocity,double mass,double radius){
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.radius = radius;
    }
}
