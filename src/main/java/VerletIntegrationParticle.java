import java.util.List;

public class VerletIntegrationParticle {
    public static Particle integrate(Particle p, List<Particle> particles, double dt, Vector target){

        Vector position = p.position;
        Vector velocity = p.velocity;
        Vector acceleration = p.fr(particles,target).nDiv(p.mass);
        velocity = velocity.nSum(acceleration.nMult(dt/2));
        position = position.nSum(velocity.nMult(dt));
        acceleration = p.fr(particles,target).nDiv(p.mass);
        velocity = velocity.nSum(acceleration.nMult(dt/2));

        return new Particle(p.id,position,p.vdi,p.mass,p.radius);
    }
}
