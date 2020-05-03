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
        while(room.particles.size() != 0){

            sb.append(room.particles.size()+4).append("\n\n");
            sb.append(limits());
            for(int j = 0; j < room.particles.size();j++){
                sb.append(room.particles.get(j).toOvito()).append("\n");
                if(!room.isOutside(room.particles.get(j))){
                    Particle np = VerletIntegrationParticle.integrate(room.particles.get(j),room.particles,ModelParams.dt,ModelParams.target1);
                    nParticles.add(np);
                }
//                if(room.isOutside(room.particles.get(j)) && !room.isNearTarget2(room.particles.get(j))){
//                    Particle np = VerletIntegrationParticle.integrate(room.particles.get(j),room.particles,ModelParams.dt,ModelParams.target2);
//                    nParticles.add(np);
//                }
            }

            room.setParticles(new LinkedList<>(nParticles));
            nParticles.clear();

            i++;
        }


        FileWriter fw = new FileWriter("sim.xyz");
        fw.write(sb.toString());
        fw.close();


    }

    private static String limits(){
        String ret = "";
        ret = ModelParams.X_max + " " + ModelParams.Y_max + " 0\n" +
                ModelParams.X_max + " " + 0 + " 0\n" +
                "0 0 0\n" +
                "0" + " " + ModelParams.Y_max + " 0\n";
        return ret;
    }

}
