import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("Social Force Model Simulation");

        Room room = new Room();

        room.generateParticles();

        List<Particle> nParticles = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(!room.isEmpty() && i < 200){

            sb.append(room.particles.size()).append("\n\n");
            for (Particle p : room.particles) {
                sb.append(p.toOvito()).append("\n");
                if(!room.isInTheDoor(p) && room.isOutside(p)){
                    Particle np = VerletIntegrationParticle.integrate(p,room.particles,ModelParams.dt,ModelParams.target1);
                    nParticles.add(np);
                }
            }

            room.particles = nParticles;
            i++;
        }


        FileWriter fw = new FileWriter("sim.xyz");
        fw.write(sb.toString());
        fw.close();


    }

}
