/*
* Author:   Sean Herman
* Date:     12/7/2013
* Lattice paths
* Problem 15 (http://projecteuler.net/problem=15)
* Starting in the top left corner of a 2×2 grid, and only being able to move to
* the right and down, there are exactly 6 routes to the bottom right corner.
* How many such routes are there through a 20×20 grid?
*
*/

import java.util.Scanner;

public class LatticePaths {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Specify the dimensions of the grid (e.g., 2) >> ");
        int size = input.nextInt();

        // 2-dimensional array, with each element representing a vertex.
        // A 2x2 lattice has 3 total (SIZE + 1) vertices.
        long[][] lattice = new long[size + 1][size + 1];

        // Edges have only 1 possible direction for movement
        for (int i = size; i >= 0; i--) {
            lattice[i][size] = 1;
            lattice[size][i] = 1;
        }

        // End point (bottom right corner) naturally has no movement options
        lattice[size][size] = 0;

        // Each element's moves equals sum of possible moves in adjoining elements
        int inner = size - 1;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = inner; j >= 0; j--) {
                if (i == j) {
                    lattice[i][j] = lattice[i + 1][j] + lattice[i][j + 1];
                } else {
                    lattice[i][j] = lattice[i + 1][j] + lattice[i][j + 1];
                    lattice[j][i] = lattice[j + 1][i] + lattice[j][i + 1];
                }
            }
            inner--;
        }

        System.out.println("Total possible routes: " + lattice[0][0]);
    }
}