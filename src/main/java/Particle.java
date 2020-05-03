

import javax.jws.WebParam;
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

        return  ei.multScalar(ModelParams.vdi).nRest(this.velocity).multScalar(this.mass/ModelParams.tau);

    }







}
