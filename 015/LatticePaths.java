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

public class LatticePaths {
    static final int SIZE = 10;
    public static void main(String[] args) {

        // 2-dimensional array, with each element representing a vertex.
        // A 2x2 lattice has 3 total (SIZE + 1) vertices.
        Integer[][] lattice = new Integer[SIZE+1][SIZE+1];

        // Populate the lattice with cell values
        int cellNum = 0;
        for (int i = 0; i < lattice.length; i++) {
            for (int j = 0; j < lattice[0].length; j++) {
                lattice[i][j] = cellNum;
                cellNum++;
            }
        }

        BinaryTree<Location> bt = new BinaryTree<Location>(new Location(0,0));
        Deque<BinaryNode<Location>> queue = new LinkedList<BinaryNode<Location>>();
        queue.addLast(bt.getRoot()); // initialize the stack with the Tree's root Node
        int routes = 0;

        // Move through the lattice.
        // At each location, try to move left (x+1, y) and to move right (x, y+1)
        // Valid moves from each location are new branches on the tree
        while (queue.peekFirst() != null) {
            BinaryNode<Location> currentNode = queue.peek();
            queue.removeFirst();
            Location newLocation;
            BinaryNode<Location> newNode;

            //System.out.println("Checking " + currentNode.getInfo() + "\n");

            if (canMoveLeft(currentNode.getInfo(), lattice)) {
                // Connect a new left Node
                newLocation = new Location(
                    currentNode.getInfo().getX() + 1,
                    currentNode.getInfo().getY()
                    );
                newNode = new BinaryNode(newLocation);
                currentNode.setLeft(newNode);
                // Queue the new Node
                queue.addLast(newNode);
            }

            if (canMoveRight(currentNode.getInfo(), lattice)) {
                // Connect a new right Node
                newLocation = new Location(
                    currentNode.getInfo().getX(),
                    currentNode.getInfo().getY() + 1
                    );
                newNode = new BinaryNode(newLocation);
                currentNode.setRight(newNode);
                // Queue the new Node
                queue.addLast(newNode);
            }

            if (currentNode.getInfo().getX() == LatticePaths.SIZE && 
                currentNode.getInfo().getY() == LatticePaths.SIZE) {
                routes++;
                System.out.print("\rCompleted route #" + routes);
            }
        }

        System.out.println("\nTotal routes: " + routes);      
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