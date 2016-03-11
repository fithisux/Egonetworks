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

import java.util.Random;

/**
 *
 * @author orbit
 */
public class Unknowns {

    boolean[][] circles;
    double[][] tau;
    double[][] theta;
    
    private Unknowns(){}

    public double getRegularization(double lambda) {
        double temp = 0;
        for (int k = 0; k < this.tau.length; k++) {
            for (int l = 0; l < this.tau[k].length; l++) {
                temp += Math.abs(this.theta[k][l]);
                temp += Math.abs(this.tau[k][l]);
            }
        }

        return lambda * temp;
    }

    public int getK() {
        return circles.length;
    }

    public boolean[][] getCircles() {
        return circles;
    }

    public double[][] getTau() {
        return tau;
    }

    public double[][] getTheta() {
        return theta;
    }


    public static Unknowns generateInitial(double lambda, int K, int nodes, int features) {
        //initialize cycle data randomly        
        Unknowns unknowns = new Unknowns();
        unknowns.tau = new double[K][features];
        unknowns.theta = new double[K][features];
        unknowns.circles = new boolean[K][nodes];
        Random rr = new Random();
        for (int x = 0; x < nodes; x++) {
            for (int k = 0; k < K; k++) {
                unknowns.circles[k][x] = rr.nextBoolean();
            }
        }
        return unknowns;
    }
    
    public Unknowns unzip(double [] params){
        
        Unknowns unknowns=new Unknowns();
        unknowns.circles=this.circles;      
        int index=0;
        
        for(int k=0;k<theta.length;k++){
            for(int x=0;x<theta[k].length;x++){
                unknowns.theta[k][x]=params[index++];
            }
        }
        for(int k=0;k<theta.length;k++){
            for(int x=0;x<theta[k].length;x++){
                unknowns.tau[k][x]=params[index++];
            }
        }
        return unknowns;
    }
    
    public double[] makeVector(){
        int index=0;
        
        double [] params=new double[2*theta.length*theta[0].length];
        
        for(int k=0;k<theta.length;k++){
            for(int x=0;x<theta[k].length;x++){
                params[index++]=this.theta[k][x];
            }
        }
        for(int k=0;k<theta.length;k++){
            for(int x=0;x<theta[k].length;x++){
                params[index++]=this.tau[k][x];
            }
        }
        return params;
    }    

    public Unknowns modifiableCircles(){
        
        Unknowns unknowns=new Unknowns();      
        unknowns.tau=this.tau;
        unknowns.theta=this.theta;
        
        int K = this.circles.length;
        int nodes=this.circles[0].length;
        
        boolean[][] tempcircles = new boolean[K][nodes];
        for (int k = 0; k < K; k++) {
            tempcircles[k] = (boolean[]) this.circles[k].clone();
        }
        
        unknowns.circles=tempcircles;
        return unknowns;
    }        

}
