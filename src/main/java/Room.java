import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    ModelParams modelParams;

    public List<Particle> particles;

    public int exitedParticles = 0;

    public void generateParticles(){
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
            }while(overlaps(new Particle(position,null,0,randR) , particles));
            double randV = modelParams.v_min + rand.nextDouble()*(modelParams.v_max - modelParams.v_min);
            double alfa = rand.nextDouble();
            Vector velocity = new Vector(randV*Math.cos(alfa),randV*Math.sin(alfa));
            particles.add(new Particle(position,velocity,modelParams.mass,randR));
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
        return p.position.y < 0;
    }

    public boolean isEmpty() {
        return exitedParticles == modelParams.N;
    }
}
