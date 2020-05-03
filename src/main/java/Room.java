import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    ModelParams modelParams;

    public List<Particle> particles;

    public void generateParticles(){
        particles = new ArrayList<>(modelParams.N);
        Random rand = new Random();
        for(int i = 0 ; i < modelParams.N ; i++){
            double randX = rand.nextDouble()*modelParams.X_max;
            double randY = rand.nextDouble()*modelParams.Y_max;
            double randV = modelParams.v_min + rand.nextDouble()*(modelParams.v_max - modelParams.v_min);
            double randR = modelParams.r_min + rand.nextDouble()*(modelParams.r_max - modelParams.r_min);
            double alfa = rand.nextDouble();
            Vector position = new Vector(randX,randY);
            Vector velocity = new Vector(randV*Math.cos(alfa),randV*Math.sin(alfa));
            particles.add(new Particle(position,velocity,modelParams.mass,randR));
        }
    }

}
