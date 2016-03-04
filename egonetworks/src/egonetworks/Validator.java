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

/**
 *
 * @author orbit
 */
public class Validator {

    public static double getBER(boolean[] circle1, boolean[] circle2) {
        int a1 = 0;
        int a2 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int x = 0; x < circle1.length; x++) {
            if (circle1[x]) {
                if (!circle2[x]) {
                    a1++;
                }
                count1++;
            } else {
                if (circle2[x]) {
                    a2++;
                }
                count2++;
            }
        }

        double b1 = a1;
        double b2 = a2;

        return 0.5 * ((b1 / (count1 + 1)) + (b2 / (count2 + 1)));
    }

    public static double evaluate(boolean[][] circles1, boolean[][] circles2) {
        double err = 0;
        for (int f = 0; f < circles1.length; f++) {
            double minimum = Validator.getBER(circles1[f], circles2[0]);
            for (int g = 1; g < circles2.length; g++) {
                double temp = Validator.getBER(circles1[f], circles2[g]);
                if (temp < minimum) {
                    minimum = temp;
                }
            }
            err += minimum;
        }

        return (err / circles1.length);
    }
}
