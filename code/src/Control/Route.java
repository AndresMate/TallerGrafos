package Control;

public class Route {
    private City startCity;
    private City endCity;
    private double distance;

    public Route(City startCity, City endCity, double distance) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.distance = calculeDistance();
    }


    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public void setEndCity(City endCity) {
        this.endCity = endCity;
    }
    private double calculeDistance() {
        double dLat = Math.toRadians(endCity.getLatitude() - startCity.getLatitude());
        double dLon = Math.toRadians(endCity.getLength() - startCity.getLength());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(startCity.getLatitude())) * Math.cos(Math.toRadians(endCity.getLatitude()))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }

    public double getDistance() {
        return distance;
    }
}
