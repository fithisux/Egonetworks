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


import solver.Unknowns;
import java.math.*;
import qpbosolver.*;
import java.util.*;
import java.util.function.*;

/**
 *
 * @author orbit
 */
public class LogLikelihood {

    public FeatureGraph g;

    public LogLikelihood(FeatureGraph g) {
        this.g = g;
    }

    public double getL(Unknowns uknowns) {
        int nodes = this.g.getNodes();
        double result = Math.sqrt(2 * uknowns.getK());
        result *= (nodes * nodes + this.g.getNumedges());

        double maximum = 0;
        for (int x = 0; x < nodes; x++) {
            for (int y = 0; y < nodes; y++) {
                double temp = 0;
                for (double d : this.g.getFeatures(x, y)) {
                    temp += (d * d);
                }
                temp = Math.sqrt(temp);

                if (maximum < temp) {
                    maximum = temp;
                }
            }
        }

        return result * maximum;

    }

    public double Phi(int x, int y, Unknowns uknowns) {
        double phi = 0;
        double[] features = this.g.getFeatures(x, y);
        for (int k = 0; k < uknowns.getK(); k++) {
            double temp = 0;
            double[] tau = uknowns.getTau()[k];
            for (int l = 0; l < features.length; l++) {
                temp += features[l] * tau[l];
            }
            phi += temp;
        }

        for (int k = 0; k < uknowns.getK(); k++) {
            if (uknowns.getCircles()[k][x] && uknowns.getCircles()[k][y]) {
                double temp = 0;
                double[] tau = uknowns.getTau()[k];
                double[] theta = uknowns.getTheta()[k];
                for (int l = 0; l < features.length; l++) {
                    temp += features[l] * (theta[l] - tau[l]);
                }
                phi += temp;
            }
        }
        return phi;
    }

    public double NakedLogLikelihood(int x, int y, Unknowns uknowns) {
        if (x == y) {
            return 0;
        }
        double term1 = 0;
        double term2 = 0;
        double phi = this.Phi(x, y, uknowns);
        if (this.g.hasEdge(x, y)) {
            term1 = phi;
        }
        term2 = Math.log(1 + Math.exp(phi));
        return term1 - term2;
    }

    public double NakedLogLikelihood(Unknowns uknowns) {
        double sum = 0;
        int nodes = this.g.getNodes();
        for (int x = 0; x < nodes; x++) {
            for (int y = 0; y < nodes; y++) {
                sum += this.NakedLogLikelihood(x, y, uknowns);
            }
        }

        return sum;
    }
    
    public double[][] derivativeofTheta(Unknowns uknowns){
        int nodes = this.g.getNodes();
        int featurenum=this.g.getFeatureDim();
        
        double [][] params=new double[nodes][featurenum];
        
        for (int k = 0; k < uknowns.getK(); k++) {
            for(int l=0;l<featurenum;l++){
                for (int x = 0; x < nodes; x++) {
                for (int y = 0; y < nodes; y++) {
                    double weight=Math.exp(this.Phi(x, y, uknowns));
                    weight = -( weight / (1+weight));
                    if(g.hasEdge(x, y)){
                        weight += 1;
                    }
                    if (uknowns.getCircles()[k][x] && uknowns.getCircles()[k][y]){
                        params[k][l] += weight * this.g.getFeatures()[x][y][l];
                    } else {
                        params[k][l] += 0;
                    }
                }
            }
            }
            
        }
        
        return params;
        
    }
    
    public double[][] derivativeofTau(Unknowns uknowns){
        int nodes = this.g.getNodes();
        int featurenum=this.g.getFeatureDim();
        
        double [][] params=new double[nodes][featurenum];
        
        for (int k = 0; k < uknowns.getK(); k++) {
            for(int l=0;l<featurenum;l++){
                for (int x = 0; x < nodes; x++) {
                for (int y = 0; y < nodes; y++) {
                    double weight=Math.exp(this.Phi(x, y, uknowns));
                    weight = -( weight / (1+weight));
                    if(g.hasEdge(x, y)){
                        weight += 1;
                    }
                    if (uknowns.getCircles()[k][x] && uknowns.getCircles()[k][y]){
                        params[k][l] += 0;
                    } else {
                        params[k][l] += weight * this.g.getFeatures()[x][y][l];
                    }
                }
            }
            }
            
        }
        
        return params;
        
    }

    public double bic(Unknowns uknowns) {
        int nodes = this.g.getNodes();
        int K = uknowns.getK();
        //return (-2 * this.NakedLogLikelihood() + this.graph.length * this.uknowns.circles.length * Math.log(this.numedges));
        return (-2 * this.NakedLogLikelihood(uknowns) + nodes * K * Math.log(nodes * (nodes - 1)));
    }

}
