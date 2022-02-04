Circle createUnitCircle(Point p, Point q) {
    Point midPoint = p.midPoint(q);
    double angle = p.angleTo(q);
    double midLength = p.distanceTo(midPoint);
    // d is sqrt(1 - (p to m))
    Point newCenter = midPoint.moveTo(angle + Math.PI/2, Math.sqrt(1 - midLength * midLength));
    return new Circle(newCenter, 1);
}

int findMaxDiscCoverage(List<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        Point currPoint = points.get(i);
        for (int j = i + 1; j < numOfPoints; j++) {	
            Point otherPoint = points.get(j);
            if (currPoint.distanceTo(otherPoint) > 2) {
                continue;
            }
            int count = 0;
            Circle unitCircle = createUnitCircle(currPoint, otherPoint);
            for (Point p: points) {
                if (unitCircle.contains(p)) {
                    count++;
                }
            }
            maxDiscCoverage = Math.max(maxDiscCoverage, count);
        }
    }
    return maxDiscCoverage;
}
