/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;
import java.util.ArrayList;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @author Ryan Cummins
 */
public class PriorityQueue {
    ArrayList<PriorityItem> _items = new ArrayList();

    public PriorityQueue() { }
    public void enqueue(PriorityItem item) {
        _items.add(item);
        bubbleUp(item);
    }

    public void printList() {
        for (PriorityItem item : _items) {
            System.out.println("Item:" + String.valueOf(item.getItem()) + " Priority: " +
                                String.valueOf(item.getPriority()));
        }
    }

    private void bubbleDown(PriorityItem item) {
        int child1 = _items.indexOf(item)*2+1;
        int child2 = _items.indexOf(item)*2+2;
        if (child1 < _items.size() && child1 >= 0 && child2 < _items.size() && child2 >= 0) {
            if (_items.get(child1).getPriority() < _items.get(child2).getPriority()) {
                if (_items.get(child1).getPriority() < item.getPriority()) {
                    swap(_items.get(child1), item);
                    bubbleDown(item);
                }
            } else if (_items.get(child2).getPriority() < item.getPriority()) {
                    swap(_items.get(child1), item);
                    bubbleDown(item);
                }
        } else if (child1 < _items.size() && child1 >= 0) {
             if (_items.get(child1).getPriority() < item.getPriority()) {
                    swap(_items.get(child1), item);
                    bubbleDown(item);
                }
        }
    }

    private void bubbleUp(PriorityItem item) {

        if (_items.indexOf(item) != 0) {
            // index is even
            if (_items.indexOf(item)%2 == 0) {
                PriorityItem parent = _items.get((_items.indexOf(item)-2)/2);
                if (parent.getPriority() > item.getPriority()) {
                    swap(parent, item);
                    bubbleUp(item);
                }
            // index is odd
            } else {
                PriorityItem parent = _items.get((_items.indexOf(item)-1)/2);
                if (parent.getPriority() > item.getPriority()) {
                    swap(parent, item);
                    bubbleUp(item);
                }
            }
        }
    }

    public boolean isEmpty() { return _items.isEmpty(); }

    private void swap(PriorityItem src, PriorityItem dest) {
        int temp = _items.indexOf(src);
        _items.set(_items.indexOf(dest), src);
        _items.set(temp, dest);
    }

    public PriorityItem dequeue() {

        swap(_items.get(0), _items.get(_items.size()-1));
        PriorityItem item = _items.remove(_items.size()-1);
        if (!isEmpty()) bubbleDown(_items.get(0));
        return item;
    }
}
