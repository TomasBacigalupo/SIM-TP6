import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args){
        System.out.println("Social Force Model Simulation");

        Room room = new Room();

        room.generateParticles();

        List<Particle> nParticles = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while(!room.isEmpty()){

            for (Particle p : room.particles) {
                sb.append(p.toOvito()).append("\n");
                //TODO: ver si esta afuera o adentro para agregarla
                nParticles.add(VerletIntegrationParticle.integrate(p,room.particles,ModelParams.dt,ModelParams.target1));
            }

            room.particles = nParticles;
            break;
        }

    }

}
