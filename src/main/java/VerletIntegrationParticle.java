import java.util.List;

public class VerletIntegrationParticle {
    public static Particle integrate(Particle p, List<Particle> particles, double dt){

        Vector position = p.position;
        Vector velocity = p.velocity;
        Vector acceleration = p.force.nDiv(p.mass);
        velocity = velocity.nSum(acceleration.nMult(dt/2));
        position = position.nSum(velocity.nMult(dt));
        //acceleration = p.fr(particles).nDiv(p.mass);
        velocity = velocity.nSum(acceleration.nMult(dt/2));

        return new Particle(position,velocity,p.mass,p.radius);
    }
}
