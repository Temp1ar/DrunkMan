package ru.spbau.korovin.se.drunkman;

import com.google.code.astar.AStar;
import ru.spbau.korovin.se.drunkman.field.FieldInformation;

import java.util.LinkedList;
import java.util.List;

public class PathFinder extends AStar<Point> {
    private Point finish = null;
    private FieldInformation field = null;

    public PathFinder(FieldInformation field, Point finish) {
        this.field = field;
        this.finish = finish;
    }

    /**
     * Check if the current node is a goal for the problem.
     *
     * @param node The node to check.
     * @return <code>true</code> if it is a goal, <code>false</else> otherwise.
     */
    @Override
    protected boolean isGoal(Point node) {
        return (node.equals(finish));
    }

    /**
     * Cost for the operation to go to <code>to</code> from
     * <code>from</from>.
     *
     * @param from The node we are leaving.
     * @param to   The node we are reaching.
     * @return The cost of the operation.
     */
    @Override
    protected Double g(Point from, Point to) {
        if (from.equals(to))
            return 0.0;

        if (field.isFree(new Point(to.x, to.y)) || to.equals(finish))
            return 1.0;

        return Double.MAX_VALUE;
    }

    /**
     * Estimated cost to reach a goal node.
     * An admissible heuristic never gives a cost bigger than the real
     * one.
     * <code>from</from>.
     *
     * @param from The node we are leaving.
     * @param to   The node we are reaching.
     * @return The estimated cost to reach an object.
     */
    @Override
    protected Double h(Point from, Point to) {
        /* Use the Manhattan distance heuristic.  */
        return (double) Math.abs(finish.x - to.x) + Math.abs(finish.y - to.y);
    }

    /**
     * Generate the successors for a given node.
     *
     * @param node The node we want to expand.
     * @return A list of possible next steps.
     */
    @Override
    protected List<Point> generateSuccessors(Point node) {
        List<Point> ret = new LinkedList<>();
        int x = node.x;
        int y = node.y;
        if (y < field.getHeight()
                && (field.isFree(new Point(x, y + 1))
                || (finish.x == x && finish.y == y + 1)))
            ret.add(new Point(x, y + 1));

        if (x < field.getWidth()
                && (field.isFree(new Point(x + 1, y))
                || (finish.x == x + 1 && finish.y == y)))
            ret.add(new Point(x + 1, y));

        if (y > 0
                && (field.isFree(new Point(x, y - 1))
                || (finish.x == x && finish.y == y - 1)))
            ret.add(new Point(x, y - 1));

        if (x > 0
                && (field.isFree(new Point(x - 1, y))
                || (finish.x == x - 1 && finish.y == y)))
            ret.add(new Point(x - 1, y));

        return ret;
    }
}
