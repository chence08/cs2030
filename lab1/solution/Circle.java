class Circle {
    private final Point center;
    private final double radius;

    Circle(Point p, double radius) {
        center = p;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at %s",
                radius, center);
    }

    boolean contains(Point q) {
        return center.distanceTo(q) <= radius;
    }
}
