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
package egonetworks;

import solver.Egosolver;
import solver.Unknowns;

/**
 *
 * @author orbit
 */
public class Egonetworks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String edgeFile="";
        String featureFile="";
        
        FeatureGraph fg=Egoreader.userFeatureGraphReader(edgeFile,featureFile);
        
        LogLikelihood ll = new LogLikelihood(fg);
        
        Unknowns uknowns=Egosolver.solve(ll, 100, 10, 1e-6);
        
        boolean [][] circles=uknowns.getCircles();
        
        int circleNo=0;
        for(boolean[] circle : circles){
            System.out.println("Circle is "+circleNo+" and contains:");
            circleNo++;
            for(int node =0 ; node < circle.length; node++){
                if(circle[node]){
                    System.out.print(" "+node);
                }
            }
            System.out.println("");
        }

    }

}
