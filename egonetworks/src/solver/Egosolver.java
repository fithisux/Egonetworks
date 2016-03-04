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
import java.math.*;

/**
 *
 * @author orbit
 */
public class Egosolver {
    
    public static Unknowns solve(LogLikelihood ll,double lambda, int K, double tolerance) {
            Unknowns unknowns = Unknowns.generateInitial(lambda,K,ll.g.getNodes(),ll.g.getFeatureDim());
            CircleSolver cslv=new CircleSolver(ll);
            double previous_value=ll.NakedLogLikelihood(unknowns) + unknowns.getRegularization();;
            double err=0;
            do {
                Unknowns u1=cslv.findCircles(unknowns);
                Unknowns u2=ProximalSolver.solve(u1, ll);
                double after_value=ll.NakedLogLikelihood(u2) + u2.getRegularization();
                System.out.println("after="+after_value+",previous="+previous_value);
                err=Math.abs(after_value-previous_value);
                previous_value=after_value;
                unknowns=u2;
            }while(err >= tolerance);
            return unknowns;
        }
    
    
}
