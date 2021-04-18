package cs5004.animator.util;

import cs5004.animator.model.Shape;
import cs5004.animator.model.buildersrc.TransAll;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a series of attributes used in Builder.
 */
public class AttributeSrcNode {
    private List<TransAll> ls;
    private String shape;

    public String getShape() {
        return shape;
    }

    public List<TransAll> getLs() {
        return ls;
    }


    /**
     * Constructor.
     *
     * @param type shape of the element
     */
    public AttributeSrcNode(String type) {
        this.ls = new LinkedList<>();
        this.shape = type;
    }


}
