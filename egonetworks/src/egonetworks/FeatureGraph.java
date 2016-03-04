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
public class FeatureGraph {

    public boolean[][] graph;
    public double[][][] features;
    int numedges;

    public FeatureGraph(boolean[][] graph, double[][][] features) {
        this.features = features;
        this.numedges = 0;
        this.graph = graph;
        for (int x = 0; x < this.graph.length; x++) {
            for (int y = 0; y < this.graph.length; y++) {
                if (this.graph[x][y]) {
                    this.numedges++;
                }
            }
        }
    }

    public int getNodes() {
        return this.graph.length;
    }

    public int getFeatureDim() {
        return this.graph.length;
    }

    public boolean hasEdge(int x, int y) {
        return graph[x][y];
    }

    public double[][][] getFeatures() {
        return features;
    }

    public int getNumedges() {
        return numedges;
    }

    public double[] getFeatures(int x, int y) {
        return this.features[x][y];
    }
}
