import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Room {
    ModelParams modelParams;

    public List<Particle> particles;

    public int exitedParticles = 0;

    public void generateParticles(){
        int id = 0;
        particles = new ArrayList<>(modelParams.N);
        Random rand = new Random();
        for(int i = 0 ; i < modelParams.N ; i++){
            double randX;
            double randY;
            Vector position;
            double randR = modelParams.r_min + rand.nextDouble()*(modelParams.r_max - modelParams.r_min);
            do {
                randX = rand.nextDouble()*modelParams.X_max;
                randY = rand.nextDouble()*modelParams.Y_max;
                position = new Vector(randX,randY);
            }while(overlaps(new Particle(0,position,0,0,randR) , particles));
            double randV = modelParams.v_min + rand.nextDouble()*(modelParams.v_max - modelParams.v_min);
            particles.add(new Particle(id,position,randV,modelParams.mass,randR));
            id++;
        }
    }

    private boolean overlaps(Particle other,List<Particle> particles) {
        for(Particle p : particles) {
            if(p.overlaps(other)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInTheDoor(Particle p){
        double door_left = modelParams.door_pos.x - modelParams.door_width/2;
        double door_right = modelParams.door_pos.x + modelParams.door_width/2;
        return p.position.x > door_left && p.position.x < door_right && p.position.y == 0;
    }

    public boolean isOutside(Particle p){
        if(0 <= p.position.y && p.position.y <= ModelParams.Y_max && 0 <= p.position.x && p.position.x <= ModelParams.X_max){
            return false;
        }

        return true;
    }

    public boolean isEmpty() {
        return exitedParticles == modelParams.N;
    }

    public void setParticles(LinkedList<Particle> particles) {
        for(Particle p : particles){
            if(this.isOutsideTheBox(p)){
                p.velocity = p.velocity.multScalar(-1);
            }
        }
        this.particles = particles;
    }

    public boolean isNearTarget2(Particle p){
        double nearDouble = 0.5;
        return (p.position.x + nearDouble >= modelParams.target2.x || p.position.x - nearDouble < modelParams.target2.x) && p.position.y - nearDouble <= modelParams.target2.y;
    }
    
    private boolean isOutsideTheBox(Particle p) {
        return p.position.x + p.radius > modelParams.X_max || p.position.x - p.radius < 0 || p.position.y + p.radius > modelParams.Y_max || p.position.y - p.radius < 0;
    }
}
