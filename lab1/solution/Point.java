class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", x, y);
    }

    Point midPoint(Point q) {
        double newX = (x + q.x) / 2;
        double newY = (y + q.y) / 2;
        return new Point(newX, newY);
    }

    double angleTo(Point q) {
        double distX = q.x - x;
        double distY = q.y - y;
        return Math.atan2(distY, distX);
    }

    Point moveTo(double theta, double d) {
        double deltaX = d * Math.cos(theta);
        double deltaY = d * Math.sin(theta);
        return new Point(x + deltaX, y + deltaY);
    }

    double distanceTo(Point other) {
        double distX = other.x - x;
        double distY = other.y - y;
        return Math.sqrt(distX * distX + distY * distY);
    }
}
