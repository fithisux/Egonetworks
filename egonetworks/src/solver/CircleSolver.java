/*
    egocirlces is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Sevirus is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.

	Contributors:
   Dr. Vasileios I. Anagnostopoulos
 */
package solver;

import egonetworks.LogLikelihood;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import qpbosolver.PairwiseEnergy;
import qpbosolver.QPBOInput;
import qpbosolver.QPBOSolution;
import qpbosolver.UnaryEnergy;

/**
 *
 * @author orbit
 */
public class CircleSolver {
    
    LogLikelihood ll;
    Random rr;
    
    public CircleSolver(LogLikelihood ll){
        this.ll=ll;
        this.rr=new Random();
    }
    public Unknowns findCircles(Unknowns unknowns){
        unknowns=unknowns.modifiableCircles();
        int K=unknowns.getK();
        
        for(int k=0;k<K;k++){
            boolean[] temp = this.solveQPBO(k, unknowns);
            unknowns.circles[k]=temp;
        }        
        return unknowns;
    }
    
    public QPBOInput FormulateQPBO(int circle, Unknowns unknowns) {
        int nodes = this.ll.g.getNodes();
        unknowns=unknowns.modifiableCircles();
        List<PairwiseEnergy> lista = new ArrayList<>();

        for (int x = 0; x < nodes; x++) {
            for (int y = 0; y < nodes; y++) {
                PairwiseEnergy temp = new PairwiseEnergy();
                temp.x = x;
                temp.y = y;
                unknowns.getCircles()[circle][x] = false;
                unknowns.getCircles()[circle][y] = false;
                temp.e_0_0 = ll.NakedLogLikelihood(x, y, unknowns);
                unknowns.getCircles()[circle][x] = false;
                unknowns.getCircles()[circle][y] = true;
                temp.e_0_1 = ll.NakedLogLikelihood(x, y, unknowns);
                unknowns.getCircles()[circle][x] = true;
                unknowns.getCircles()[circle][y] = false;
                temp.e_1_0 = ll.NakedLogLikelihood(x, y, unknowns);
                unknowns.getCircles()[circle][x] = true;
                unknowns.getCircles()[circle][y] = true;
                temp.e_1_1 = ll.NakedLogLikelihood(x, y, unknowns);
                lista.add(temp);
            }
        }

        QPBOInput input = new QPBOInput();
        input.nodes = nodes;
        input.edges = nodes * (nodes - 1);
        input.unaryenergies = new UnaryEnergy[0];
        input.pairwiseenergies = lista.toArray(new PairwiseEnergy[0]);
        return input;
    }
    
    public boolean[] solveQPBO(int circle,Unknowns unknowns){
        QPBOSolution solution = new  QPBOSolution(); 
        QPBOInput input=this.FormulateQPBO(circle, unknowns);
        solution.create_qpbo(input.nodes,input.edges);
        boolean[] flags=new boolean[input.nodes];
        int index=0;
        for(UnaryEnergy ue : input.unaryenergies){
            solution.add_unary_energies(index++,ue.e_0,ue.e_1);
        }
        
        for(PairwiseEnergy pe : input.pairwiseenergies){
            solution.add_pairwise_energies(pe.x,pe.y,pe.e_0_0,pe.e_0_1,pe.e_1_0,pe.e_1_1);
        }
        
        solution.find_solution();
                
        for(int i=0;i<input.nodes;i++){
            int temp=solution.get_flag(i);
            System.out.println("flag "+Integer.toString(i)+" has value "+temp);
            
            if (temp == 0){
                flags[i]=false;
            } else if (temp == 1) {
                flags[i]=true;
            } else {
                flags[i]=rr.nextBoolean();
            }
        }
        
        solution.dispose_qpbo();
        return flags;
    }
    
}
