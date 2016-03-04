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
public class QPBOSolution {
    
     static {
      System.loadLibrary("libjnicpbo"); // hello.dll (Windows) or libhello.so (Unixes)
   }
    
    public native void create_qpbo(int nodes,int edges);
    public native void add_unary_energies(int node,double e_0,double e_1);
    public native void add_pairwise_energies(int x,int y,double e_0_0,double e_0_1,double e_1_0,double e_1_1);
    public native void find_solution();
    public native int get_flag(int node);
    public native void dispose_qpbo();
    
}
