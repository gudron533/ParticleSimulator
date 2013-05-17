/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.CookieCrumbles;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * General vector class
 *
 * @author Alex Mamyshev, R. Seyidov;
 * @version Long time ago in a far far galaxy.
 */
public class CCVector {

    private CartesianCoordinate i;
    private PolarCoordinate j;

    public CCVector(CartesianCoordinate i) {
        this.i = i;
    }

    public CCVector(PolarCoordinate j) {
        this.j = j;
    }

    public CCVector(CCVector v) {
        i = v.i;
        j = v.j;
    }

    public CCVector(CartesianCoordinate i, PolarCoordinate j) {
        this.i = i;
        this.j = j;
    }

    public CCVector add(final CCVector other) {
        if (j == null) {
            // adding two vectors here
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            double x = i.getX();
            double y = i.getY();
            double y2 = other.i.getY();
            double x2 = other.i.getX();
            double x3 = x2 + x;
            double y3 = y2 + y;
            k.setX(x3);
            k.setY(y3);

            // Calulating polar coords
            PolarCoordinate h = new PolarCoordinate(0, 0);
            double power = 2;
            double x_power = Math.pow(x3, power);
            double y_power = Math.pow(y3, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(y3, x3);
            azimuth = Math.toDegrees(azimuth);
            h.setAzimuth(azimuth);
            h.setLength(length);

            //generating return value 
            CCVector result = new CCVector(k, h);
            return result;
        } else {
            // Finding Cartesian coords of vector 1 
            double az1 = j.getAzimuth();
            az1 = Math.toRadians(az1);
            double len1 = j.getLength();
            double x1 = len1 * Math.cos(az1);
            double y1 = len1 * Math.sin(az1);

            //finding Cart Coords of vector 2
            double az2 = other.j.getAzimuth();
            az2 = Math.toRadians(az2);
            double len2 = other.j.getLength();
            double x2 = len2 * Math.cos(az2);
            double y2 = len2 * Math.sin(az2);

            //Adding coords as usual 
            double x3 = x2 + x1;
            double y3 = y2 + y1;

            //Back to polar coords 
            double power = 2;
            double x_power = Math.pow(x3, power);
            double y_power = Math.pow(y3, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(y3, x3);
            azimuth = Math.toDegrees(azimuth);

            //Returning values 
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            k.setX(x3);
            k.setY(y3);
            PolarCoordinate h = new PolarCoordinate(0, 0);
            h.setAzimuth(azimuth);
            h.setLength(length);
            CCVector result = new CCVector(k, h);
            return result;
        }
    }
    


    public CCVector subtract(final CCVector other) {
        if (j == null) {
            // subtracting two vectors here
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            double x = i.getX();
            double y = i.getY();
            double y2 = other.i.getY();
            double x2 = other.i.getX();
            double x3 = x2 - x;
            double y3 = y2 - y;
            k.setX(x3);
            k.setY(y3);

            // Calulating polar coords
            PolarCoordinate h = new PolarCoordinate(0, 0);
            double power = 2;
            double x_power = Math.pow(x3, power);
            double y_power = Math.pow(y3, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(y3, x3);
            azimuth = Math.toDegrees(azimuth);
            h.setAzimuth(azimuth);
            h.setLength(length);

            //generating return value 
            CCVector result = new CCVector(k, h);
            return result;
        } else {
            // Finding Cartesian coords of vector 1 
            double az1 = j.getAzimuth();
            az1 = Math.toRadians(az1);
            double len1 = j.getLength();
            double x1 = len1 * Math.cos(az1);
            double y1 = len1 * Math.sin(az1);

            //finding Cart Coords of vector 2
            double az2 = other.j.getAzimuth();
            az2 = Math.toRadians(az2);
            double len2 = other.j.getLength();
            double x2 = len2 * Math.cos(az2);
            double y2 = len2 * Math.sin(az2);

            //Subtracting coords as usual 
            double x3 = x1 - x2;
            double y3 = y1 - y2;

            //Back to polar coords 
            double power = 2;
            double x_power = Math.pow(x3, power);
            double y_power = Math.pow(y3, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(y3, x3);
            azimuth = Math.toDegrees(azimuth);

            //Returning values 
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            k.setX(x3);
            k.setY(y3);
            PolarCoordinate h = new PolarCoordinate(0, 0);
            h.setAzimuth(azimuth);
            h.setLength(length);
            CCVector result = new CCVector(k, h);
            return result;
        }

    }

    public double dotProduct(final CCVector other) {
        if (j == null) {
            double x = i.getX();
            double y = i.getY();
            double y2 = other.i.getY();
            double x2 = other.i.getX();
            double x3 = x2 * x;
            double y3 = y2 * y;
            double product = y3 + x3;
            return product;
        } else {
            // Finding Cartesian coords of vector 1 
            double az1 = j.getAzimuth();
            az1 = Math.toRadians(az1);
            double len1 = j.getLength();
            double x1 = len1 * Math.cos(az1);
            double y1 = len1 * Math.sin(az1);

            //finding Cart Coords of vector 2
            double az2 = other.j.getAzimuth();
            az2 = Math.toRadians(az2);
            double len2 = other.j.getLength();
            double x2 = len2 * Math.cos(az2);
            double y2 = len2 * Math.sin(az2);

            // finding dot product here
            double x3 = x2 * x1;
            double y3 = y2 * y1;
            double product = y3 + x3;
            return product;
        }
    }

    public CCVector scale(final double scaleFactor) {
        if (j == null) {
            // finding Cartesian values
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            double x = i.getX();
            double y = i.getY();
            double x_scaled = x * scaleFactor;
            double y_scaled = y * scaleFactor;
            k.setX(x_scaled);
            k.setY(y_scaled);

            // Calculating polar coords
            PolarCoordinate h = new PolarCoordinate(0, 0);
            double power = 2;
            double x_power = Math.pow(x_scaled, power);
            double y_power = Math.pow(y_scaled, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(x_scaled, y_scaled);
            azimuth = Math.toDegrees(azimuth);
            h.setAzimuth(azimuth);
            h.setLength(length);

            // Return value  
            CCVector result = new CCVector(k, h);
            return result;
        } else {
            // finding cartesian coords 
            double az1 = j.getAzimuth();
            az1 = Math.toRadians(az1);
            double len1 = j.getLength();
            double x1 = len1 * Math.cos(az1);
            double y1 = len1 * Math.sin(az1);

            // scaling 
            double x_scaled = x1 * scaleFactor;
            double y_scaled = y1 * scaleFactor;

            //Back to polar coords 
            double power = 2;
            double x_power = Math.pow(x_scaled, power);
            double y_power = Math.pow(y_scaled, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(y_scaled, x_scaled);
            azimuth = Math.toDegrees(azimuth);


            //Returning values 
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            k.setX(x_scaled);
            k.setY(y_scaled);
            PolarCoordinate h = new PolarCoordinate(0, 0);
            h.setAzimuth(azimuth);
            h.setLength(length);
            CCVector result = new CCVector(k, h);
            return result;
        }
    }
    
    public double getAzimuth(){
        return j.getAzimuth();
    }

//    public double getAzimuth() {
//        if (j == null) {
//            double x = i.getX();
//            double y = i.getY();
//            double azimuth = Math.atan2(y, x);
//            azimuth = Math.toDegrees(azimuth);
//            return azimuth;
//        } else {
//            double azimuth = j.getAzimuth();
//            azimuth = Math.toDegrees(azimuth);
//            return azimuth;
//        }
//
//    }

    public double getX() {
        return i.getX();

    }

    public double getY() {
        return i.getY();
    }

    public void setCoord(double x, double y) {
        i.setX(x);
        i.setY(y);
    }
    
    public void setAzi(double azi){
        j.setAzimuth(azi);
    }

    public double getLength() {
        if (j == null) {
            double x = i.getX();
            double y = i.getY();
            double power = 2;
            double x_power = Math.pow(x, power);
            double y_power = Math.pow(y, power);
            double length = Math.sqrt(x_power + y_power);
            return length;
        } else {
            double length = j.getLength();
            return length;
        }
    }

    public CCVector normalize() {
        if (j == null) {
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            double x = i.getX();
            double y = i.getY();

            // finding length 
            PolarCoordinate h = new PolarCoordinate(0, 0);
            double power = 2;
            double x_power = Math.pow(x, power);
            double y_power = Math.pow(y, power);
            double length = Math.sqrt(x_power + y_power);
            double azimuth = Math.atan2(y, x);
            azimuth = Math.toDegrees(azimuth);
            h.setAzimuth(azimuth);

            //normalizing here
            double x_normal = x / length;
            double y_normal = y / length;
            h.setLength(1);
            k.setX(x_normal);
            k.setY(y_normal);

            // returning values 
            CCVector result = new CCVector(k, h);
            return result;
        } else {
            // finding cartesian coords 
            double az1 = j.getAzimuth();
            az1 = Math.toRadians(az1);
            double len1 = j.getLength();
            double x = len1 * Math.cos(az1);
            double y = len1 * Math.sin(az1);

            // getting length and normalizing
            double length = j.getLength();
            double x_norm = x / length;
            double y_norm = y / length;

            //returning values
            PolarCoordinate h = new PolarCoordinate(0, 0);
            CartesianCoordinate k = new CartesianCoordinate(0, 0);
            az1 = Math.toDegrees(az1);
            h.setAzimuth(az1);
            h.setLength(1);
            k.setX(x_norm);
            k.setY(y_norm);
            CCVector result = new CCVector(k, h);
            return result;
        }
    }

    public CCVector move(CCVector originalPosition, double travelTime) {
        double velocity = j.getLength();
        double originalPos = originalPosition.j.getLength();
        double finalpos = velocity * travelTime + originalPos;
        double direction = originalPosition.j.getAzimuth();
        double x2 = finalpos * Math.cos(direction);
        double y2 = finalpos * Math.sin(direction);
        double azimuth = Math.atan2(y2, x2);
        //azimuth = Math.toDegrees(azimuth);

        PolarCoordinate h = new PolarCoordinate(azimuth, finalpos);
        CartesianCoordinate m = new CartesianCoordinate(x2, y2);
        CCVector result = new CCVector(m, h);
        return result;

    }
}
//    public CCVector move(CCVector originalPosition, double travelTime) {
//        double CCVector = j.getLength();
////        if (CCVector> 0){
//            //CCVector = CCVector - 5.5;
//            //System.out.println(CCVector);
//            double originalPos = originalPosition.j.getLength();
//            //originalPos = originalPos-3;
//            double finalpos = CCVector * travelTime + originalPos;
//            finalpos = finalpos - 1;
//            double direction = originalPosition.j.getAzimuth();
//            direction = direction + 0.02;
//            double x2 = finalpos * Math.cos(direction);
//            x2 = x2 - 1;
//            double y2 = finalpos * Math.sin(direction);
//            y2 = y2 -1;
//            //direction = direction - 1;
//            //finalpos = finalpos - 5;
//            PolarCoordinate h = new PolarCoordinate(direction, finalpos);
//            CartesianCoordinate m = new CartesianCoordinate(x2, y2);
//            CCVector result = new CCVector(m, h);
//            return result;
//    }
//}
