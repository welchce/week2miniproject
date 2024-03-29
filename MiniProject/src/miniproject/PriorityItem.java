/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @author Ryan Cummins
 */
public class PriorityItem {
    //The maximum allowed priority in the Queue.
    static final int MAX_PRIORITY = 200;
    //The minimum allowed priority in the Queue.
    static final int MIN_PRIORITY = 0;
    //The priority of the item.
    int _priority;
    //the char[] array for the item.
    char _item[] = new char[5];

    /*
     *Construtor for the item that assigns the passed values into the item array
     * and the priority.
     */
    public PriorityItem(char item[], int priority) throws IndexOutOfBoundsException {
        if (priority > MAX_PRIORITY || priority < MIN_PRIORITY)
            throw new IndexOutOfBoundsException();
        _priority = priority;
        _item = item;
    }
    /**
     *
     * @return the item array.
     */
    public char[] getItem() { return _item; }
    /**
     *
     * @return the priority of the PriorityItem.
     */
    public int getPriority() { return _priority; }
}
