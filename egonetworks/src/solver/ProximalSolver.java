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

import egonetworks.FeatureGraph;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import qpbosolver.PairwiseEnergy;
import qpbosolver.QPBOInput;
import qpbosolver.UnaryEnergy;
import egonetworks.*;
import java.util.function.*;
import qpbosolver.QPBOSolution;

/**
 *
 * @author orbit
 */
public class ProximalSolver {

    public static class Objective implements Function<double[], Double> {

        public Double apply(double[] x) {
            return ll.NakedLogLikelihood(unknowns.unzip(x)) + unknowns.getRegularization(this.lambda);
        }

        Unknowns unknowns;
        LogLikelihood ll;
        double lambda;

        public Objective(Unknowns unknowns, LogLikelihood ll,double lambda) {
            this.unknowns = unknowns;
            this.ll = ll;
            this.lambda=lambda;
        }
    }

    public static class Derivative implements Function<double[], double[]> {

        public double[] apply(double[] loc) {

            Unknowns temp=unknowns.unzip(loc);
            double[][] dertheta = ll.derivativeofTheta(temp);
            double[][] dertau = ll.derivativeofTau(temp);

            double[] params = new double[2 * dertheta.length * dertheta[0].length];
            int index = 0;
            for (int k = 0; k < dertheta.length; k++) {
                for (int x = 0; x < dertheta[k].length; x++) {
                    params[index++] = dertheta[k][x];
                }
            }
            for (int k = 0; k < dertheta.length; k++) {
                for (int x = 0; x < dertheta[k].length; x++) {
                    params[index++] = dertau[k][x];
                }
            }
            return params;
        }

        Unknowns unknowns;
        LogLikelihood ll;

        public Derivative(Unknowns unknowns, LogLikelihood ll) {
            this.unknowns = unknowns;
            this.ll = ll;
        }
    }
    
    
    public static Unknowns solve(Unknowns unknowns, LogLikelihood ll,double lambda){
        double L=ll.getL(unknowns);
        Derivative derivative=new Derivative(unknowns,ll);
        Objective objective=new Objective(unknowns,ll,lambda);
        ProximalMethod pxm = new ProximalMethod(objective,derivative,lambda,L);        
        double [] ending=pxm.solve(unknowns.makeVector(), 0.01);        
        return unknowns.unzip(ending);
    }
    
}
