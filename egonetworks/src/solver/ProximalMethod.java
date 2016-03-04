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


import java.util.function.*;
import java.math.*;

/**
 *
 * @author orbit
 */
public class ProximalMethod {

    Function<double[], Double> objective;
    Function<double[], double[]> derivative;
    double lambda;
    double L;

    ProximalMethod(Function<double[], Double> objective, Function<double[], double[]> derivative, double lambda, double L) {
        this.lambda = lambda;
        this.L = L;
        this.objective = objective;
        this.derivative = derivative;
    }

    public double[] proximal_projection(double mu, double[] x) {
        double[] result = (double[]) x.clone();
        for (int i = 0; i < x.length; i++) {

            if (Math.abs(x[i]) > mu) {
                result[i] = x[i] - mu * Math.signum(x[i]);
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    public double[] solve(double[] initial, double tolerance) {
        double[] x = (double[]) initial.clone();
        double err = 0;
        do {
            double[] direction = (double[]) x.clone();
            double[] temp = this.derivative.apply(x);
            for (int i = 0; i < x.length; i++) {
                direction[i] = x[i] - temp[i] / this.L;
            }
            double[] next_x = this.proximal_projection(this.lambda / this.L, direction);
            err = 0;
            for (int i = 0; i < x.length; i++) {
                err += ((next_x[i] - x[i]) * (next_x[i] - x[i]));
            }
            err = Math.sqrt(err);
        } while (err >= tolerance);
        return x;
    }
}
