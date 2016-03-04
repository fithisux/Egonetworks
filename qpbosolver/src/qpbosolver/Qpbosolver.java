/*
    qpboslover is free software: you can redistribute it and/or modify
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
package qpbosolver;
/**
 *
 * @author orbit
 */
public class Qpbosolver {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QPBOSolution solution = new  QPBOSolution();       
        
        QPBOInput input = new QPBOInput();
        input.nodes=2;
        input.edges=1;
        UnaryEnergy u1=new UnaryEnergy();
        u1.e_0=0;
        u1.e_1=2;
        UnaryEnergy u2=new UnaryEnergy();
        u1.e_0=3;
        u1.e_1=6;
        PairwiseEnergy p1=new PairwiseEnergy();
        p1.x=0;
        p1.y=1;
        p1.e_0_0=2;
        p1.e_0_1=3;
        p1.e_1_0=4;
        p1.e_1_1=6;
        
        input.unaryenergies=new UnaryEnergy[]{u1,u2};
        input.pairwiseenergies=new PairwiseEnergy[]{p1};
        int[] flags=new int[input.nodes];
        
        solution.create_qpbo(input.nodes,input.edges);
        int index=0;
        for(UnaryEnergy ue : input.unaryenergies){
            solution.add_unary_energies(index++,ue.e_0,ue.e_1);
        }
        
        for(PairwiseEnergy pe : input.pairwiseenergies){
            solution.add_pairwise_energies(pe.x,pe.y,pe.e_0_0,pe.e_0_1,pe.e_1_0,pe.e_1_1);
        }
        
        solution.find_solution();
        
                
        for(int i=0;i<input.nodes;i++){
            flags[i]=solution.get_flag(i);
            System.out.println("flag "+Integer.toString(i)+" has value "+flags[i]);
        }
        
        solution.dispose_qpbo();
    }    
}
