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

import java.util.Deque;
import java.util.LinkedList;
import java.text.DecimalFormat;
import java.util.Scanner;

public class LatticePaths {
    //static final int SIZE = 20;
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Specify the dimensions of the grid (e.g., 2) >> ");
        int size = input.nextInt();

        long start = System.currentTimeMillis();

        // 2-dimensional array, with each element representing a vertex.
        // A 2x2 lattice has 3 total (SIZE + 1) vertices.
        Integer[][] lattice = new Integer[size + 1][size + 1];

        // Populate the lattice with cell values
        int cellNum = 0;
        for (int i = 0; i < lattice.length; i++) {
            for (int j = 0; j < lattice[0].length; j++) {
                lattice[i][j] = cellNum;
                cellNum++;
            }
        }

        //BinaryTree<Location> bt = new BinaryTree<Location>(new Location(0,0));
        Deque<Location> queue = new LinkedList<Location>();
        queue.addLast(new Location(0,0)); // initialize the stack with the Tree's root Node
        int routes = 0;

        // Move through the lattice.
        // At each location, try to move left (x+1, y) and to move right (x, y+1)
        // Valid moves from each location are new branches on the tree
        while (queue.peekFirst() != null) {
            Location currentLocation = queue.peek();
            queue.removeFirst();
            
            Location newLocation;
            //BinaryNode<Location> newNode;

            //System.out.println("Checking " + currentNode.getInfo() + "\n");

            if (canMoveLeft(currentLocation, lattice)) {
                // Connect a new left Node
                newLocation = new Location(
                    currentLocation.getX() + 1,
                    currentLocation.getY()
                    );
                //newNode = new BinaryNode(newLocation);
                //currentNode.setLeft(newNode);
                // Queue the new Node
                queue.addLast(newLocation);
            }

            if (canMoveRight(currentLocation, lattice)) {
                // Connect a new right Node
                newLocation = new Location(
                    currentLocation.getX(),
                    currentLocation.getY() + 1
                    );
                //newNode = new BinaryNode(newLocation);
                //currentNode.setRight(newNode);
                // Queue the new Node
                queue.addLast(newLocation);
            }

            if (currentLocation.getX() == size && 
                currentLocation.getY() == size) {
                routes++;
                System.out.print("\rCompleted route # " + routes);
            }
        }

        long finish = System.currentTimeMillis();
        DecimalFormat df = new DecimalFormat("#.#");

        double runtime = (finish - start) / 1000.0;

        System.out.printf("\nCompleted %d routes in %f seconds.\n", routes, runtime);      
    }

    public static boolean canMoveLeft(Location loc, Integer[][] lattice) {
        try {
            int val = lattice[loc.getX() + 1][loc.getY()]; // move 1 place left
            return true; // can move 1 left
        } catch (IndexOutOfBoundsException e) {
            return false; // cannot move 1 left
        }
    }

    public static boolean canMoveRight(Location loc, Integer[][] lattice) {
        try {
            int val = lattice[loc.getX()][loc.getY() + 1]; // move 1 place right
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false; // cannot move 1 right 
        }
    }
}

class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else
            throw new IllegalArgumentException("Location coordinates must be > 0,0.");
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override public String toString() {
        String out = ("Location: (" + x + "," + y + ")");
        return out;
    }
}