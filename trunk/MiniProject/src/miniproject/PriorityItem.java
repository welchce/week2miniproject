/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class PriorityItem {
    static final int MAX_PRIORITY = 200;
    static final int MIN_PRIORITY = 0;
    int _priority;
    Object _item;

    public PriorityItem(Object item, int priority) throws IndexOutOfBoundsException {
        if (priority > MAX_PRIORITY || priority < MIN_PRIORITY)
            throw new IndexOutOfBoundsException();
        _priority = priority;
        _item = item;
    }

    public Object getItem() { return _item; }
    public int getPriority() { return _priority; }
}
