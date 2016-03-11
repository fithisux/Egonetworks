/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egonetworks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author orbit
 */
public class Egoreader {

    public static List<String[]> readStrippedFields(String filename) {
        BufferedReader br = null;
        List<String[]> combs = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filename));
            boolean finished = false;
            do {
                String line = br.readLine();
                if ((line != null) && !line.isEmpty()) {
                    String[] linefields = line.split("\\s+");
                    combs.add(linefields);
                } else {
                    finished = true;
                }
            } while (!finished);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return combs;
    }

    public static boolean[][] cycleReader(int nodes, String cyclesFileName) {
        List<boolean[]> combs = new ArrayList<>();
        List<String[]> separatedfields = Egoreader.readStrippedFields(cyclesFileName);
        for (String[] linefields : separatedfields) {
            boolean[] cycle = new boolean[nodes];
            for (String field : linefields) {
                int node = Integer.parseInt(field);
                cycle[node] = true;
            }
            combs.add(cycle);
        }
        boolean[][] cycles = new boolean[combs.size()][];
        int index = 0;
        for (boolean[] cycle : combs) {
            cycles[index++] = cycle;
        }
        return cycles;
    }

    public static boolean[][] edgeReader(String edgeFileName) {
        Set<Point> edgepoints = new HashSet<Point>();
        int upper = Integer.MIN_VALUE;
        int lower = Integer.MAX_VALUE;
        List<String[]> separatedfields = Egoreader.readStrippedFields(edgeFileName);
        for (String[] endpoints : separatedfields) {
            int from = Integer.parseInt(endpoints[0]);
            int to = Integer.parseInt(endpoints[1]);
            if (from > upper) {
                upper = from;
            }
            if (to > upper) {
                upper = to;
            }

            if (from < lower) {
                lower = from;
            }
            if (to < lower) {
                lower = to;
            }
            edgepoints.add(new Point(from, to));
        }

        if ((upper == Integer.MIN_VALUE) || (lower == Integer.MAX_VALUE)) {
            return null;
        }

        if (lower != 0) {
            return null;
        }

        if (upper <= 0) {
            return null;
        }

        int nodes = upper + 1;

        boolean[][] edges = new boolean[nodes][nodes];

        for (Point p : edgepoints) {
            edges[p.x][p.y] = true;
        }

        return edges;
    }

    public static double[][] featureReader(int nodes, String featureFileName) {
        double[][] features = new double[nodes][];
        List<String[]> separatedfields = Egoreader.readStrippedFields(featureFileName);
        for (String[] linefields : separatedfields) {
            int node = Integer.parseInt(linefields[0]);
            double[] sfeats = new double[linefields.length - 1];
            for (int i = 0; i < linefields.length - 1; i++) {
                sfeats[0] = Double.parseDouble(linefields[i + 1]);
            }
            features[node] = sfeats;
        }
        if (features[0].length == 0) {
            return null;
        }

        int checkpoint = features[0].length;
        for (double[] feature : features) {
            if ((feature == null) || (feature.length != checkpoint)) {
                return null;
            }
        }
        return features;
    }

    public static FeatureGraph userFeatureGraphReader(String edgeFileName, String featureFileName) {
        boolean[][] edges = Egoreader.edgeReader(edgeFileName);
        int nodes = edges.length;
        double[][] features = Egoreader.featureReader(nodes, featureFileName);
        int checkpoint = features[0].length;
        double[][][] pairwisefeatures = new double[nodes][nodes][checkpoint];
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                for (int l = 0; l < checkpoint; l++) {
                    pairwisefeatures[i][j][l] = features[i][l] == features[j][l] ? 1 : 0;
                }
            }
        }
        return new FeatureGraph(edges, pairwisefeatures);
    }
}
