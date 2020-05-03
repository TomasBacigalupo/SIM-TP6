

import javax.jws.WebParam;
import java.util.List;

public class Particle {

    double radius;
    double mass;
    Vector position;
    Vector velocity = new Vector(0,0);
    double vdi;
    Vector force;


    public Vector socialForce(List<Particle> influence){
        Vector force = new Vector(0,0);
        double Eij = 0;
        Vector eij;
        for(Particle p : influence){

            //necesito la distancia a p y versor de direccion

            if(!this.equals(p)){
                Eij = this.position.distance(p.position)-this.radius-p.radius;
                eij = p.position.nRest(this.position).multScalar(1/this.position.distance(p.position));
                double forceMod = ModelParams.A * Math.exp(-Eij/ModelParams.B);
                Vector forceVector = eij.multScalar(forceMod);
                force.sum(forceVector);
            }

        }

        return force;
    }

    public Vector drivingForce(Vector target){

        //calculo versor
        Vector ei = this.position.nRest(target).multScalar(1/this.position.distance(target));

        return  ei.multScalar(this.vdi).nRest(this.velocity).multScalar(this.mass/ModelParams.tau);

    }

    public Vector fr(List<Particle> influence, Vector target){
        Vector fr = this.socialForce(influence);
        fr.sum(this.drivingForce(target));
        return fr;
    }


    public Particle(Vector position,double vdi,double mass,double radius){
        this.position = position;
        this.vdi = vdi;
        this.mass = mass;
        this.radius = radius;
    }


    public boolean overlaps(Particle other) {
        double dx = this.position.x - other.position.x;
        double dy = this.position.y - other.position.y;
        double dr = this.radius + other.radius;
        return dx*dx + dy*dy < dr*dr ;
    }

    public String toOvito(){
        return this.position.toString() + " " + this.radius;
    }


    public boolean equals(Particle other){
        if(this.mass != other.mass){
            return false;
        }
        if(this.radius != other.radius){
            return false;
        }

        if(this.position != other.position){
            return false;
        }
        if(this.velocity != other.velocity){
            return false;
        }
        return true;

    }

}
