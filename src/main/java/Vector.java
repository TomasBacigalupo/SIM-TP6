public class Vector {
    double x;
    double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double distance(Vector otherPoint){

        return Math.sqrt(Math.pow(this.x - otherPoint.x,2)+Math.pow(this.y-otherPoint.y,2));

    }

    public Vector nRest(Vector other){
        return new Vector(this.x-other.x,this.y-other.y);
    }

    public Vector multScalar(double scalar ){
        return new Vector(this.x *scalar,this.y * scalar);
    }

    public void sum(Vector other){
        this.x += other.x;
        this.y += other.y;
    }

    public Vector nMult(double scalar) {
        return new Vector(this.x*scalar,this.y*scalar);
    }
    public Vector nSum(Vector v) { return new Vector(this.x + v.x, this.y + v.y);
    }
    public Vector nDiv(double scalar) {
        return new Vector(x / scalar, y / scalar);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

