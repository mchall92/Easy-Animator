package cs5004.animator.util;

import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;
import cs5004.animator.model.WindowImpl;
import cs5004.animator.model.buildersrc.*;
import cs5004.animator.util.AnimationBuilder;

import cs5004.animator.util.AttributeSrcNode;

import java.util.*;

/**
 * This class injects the data from input file and input command into the model.
 */
public final class Builder implements AnimationBuilder<Window> {

    private int x;
    private int y;
    private int width;
    private int height;
    private HashMap<String, AttributeSrcNode> attributeSrc;
    private List<TransMove> transMoves;
    private List<TransChangeColor> transChangeColors;
    private List<TransScale> transScales;

    /**
     * Construct three collections to record motion information of three different motions.
     * Also records all information of transformation in a hashmap.
     */
    public Builder() {
        this.transMoves = new ArrayList<>();
        this.transChangeColors = new ArrayList<>();
        this.transScales = new ArrayList<>();
        this.attributeSrc = new HashMap<>();
    }

    /**
     * This method helps to convert a string representation of
     * a shape to a shape enum(ignoring case).
     *
     * @param shape string representation of a shape
     * @return the corresponding shape enum
     */
    private Shape findShapeHelper(String shape) {
        for (Shape s : Shape.values()) {
            if (shape.equalsIgnoreCase(s.getInputName())) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Window build() {
        Window window = new WindowImpl(x, y, width, height);
        for (String name : attributeSrc.keySet()) {
            String shape = attributeSrc.get(name).getShape();
            List<TransAll> tmpLs = attributeSrc.get(name).getLs();
            tmpLs.sort(Comparator.comparingInt(TransAll::getFromTime));
            TransAll start = tmpLs.get(0);
            TransAll end = tmpLs.get(tmpLs.size() - 1);
            window.addElement(name,
                    start.getX(),
                    start.getY(),
                    start.getR(),
                    start.getG(),
                    start.getB(),
                    findShapeHelper(shape),
                    start.getFirstArg(),
                    start.getSecondArg(),
                    start.getFromTime(),
                    end.getToTime());
        }

        for (TransMove move : transMoves) {
            window.move(move.getName(),
                    move.getX(),
                    move.getY(),
                    move.getFromTime(),
                    move.getToTime());
        }
        for (TransChangeColor color : transChangeColors) {
            window.changeColor(color.getName(),
                    color.getR(),
                    color.getG(),
                    color.getB(),
                    color.getFromTime(),
                    color.getToTime());
        }
        for (TransScale scale : transScales) {
            window.scale(scale.getName(),
                    scale.getFirstArg(),
                    scale.getSecondArg(),
                    scale.getFromTime(),
                    scale.getToTime());
        }
        transScales.clear();
        transChangeColors.clear();
        transMoves.clear();
        attributeSrc.clear();
        return window;
    }

    @Override
    public AnimationBuilder<Window> setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public AnimationBuilder<Window> declareShape(String name, String shape) {
        this.attributeSrc.put(name, new AttributeSrcNode(shape));
        return this;
    }

    @Override
    public AnimationBuilder<Window> addMotion(String name, int t1, int x1, int y1,
                                              int w1, int h1, int r1, int g1,
                                              int b1, int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2) {
        this.attributeSrc.get(name).getLs().add(new TransAll(name, t1, t2, x1,
                y1, r1, g1, b1, w1, h1));
        if (x1 != x2 || y1 != y2) {
            this.transMoves.add(new TransMove(name, t1, t2, x2, y2));
        }
        if (r1 != r2 || g1 != g2 || b1 != b2) {
            this.transChangeColors.add(new TransChangeColor(name, t1, t2, r2, g2, b2));
        }
        if (w1 != w2 || h1 != h2) {
            this.transScales.add(new TransScale(name, t1, t2, w1, h1));
        }
        return this;
    }
}
