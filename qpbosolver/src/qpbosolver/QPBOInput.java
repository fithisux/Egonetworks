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
import java.util.*;

/**
 *
 * @author orbit
 */
public class QPBOInput{
    public int nodes;
    public int edges;
    public PairwiseEnergy[] pairwiseenergies;
    public UnaryEnergy[]	 unaryenergies;    
}