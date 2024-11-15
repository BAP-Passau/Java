import java.io.Serializable;

public class Position implements Serializable {
    private Integer x;
    private Integer y;
    private Integer z;

    public Position() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Position position) {
        return this.x.equals(position.getX()) && this.y.equals(position.getY()) && this.z.equals(position.getZ());
    }

    public double distanceTo(Position pos) {
        return Math.sqrt(Math.pow(pos.x - this.x, 2) + Math.pow((pos.y - this.y), 2) + Math.pow((pos.z - this.z), 2));
    }

    public String getPositionString() {
        return this.getPositonString("2d");
    }

    public String getPositonString(String type) {
        switch (type.toLowerCase()) {
            case "2d" -> {
                return "x: " + this.x  + ", y: " + this.y;
            }
            case "3d" -> {
                return "x: " + this.x + ", y: " + this.y + ", z: " + this.z;
            }
            default -> {
                return type + " is not a valid position type";
            }
        }

        /*
        if (type.toLowerCase().equals("2d")) {
            return "x: " + this.x  + ", y: " + this.y;
        } else if (type.toLowerCase().equals("3d")) {
            return "x: " + this.x + ", y: " + this.y + ", z: " + this.z;
        } else {
            return type + " is not a valid position type";
        }

         */
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
