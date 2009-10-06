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
    int _priority;
    Object _item;

    public PriorityItem(Object item, int priority) {
        _priority = priority;
        _item = item;
    }

    public Object getItem() { return _item; }
    public int getPriority() { return _priority; }
}
