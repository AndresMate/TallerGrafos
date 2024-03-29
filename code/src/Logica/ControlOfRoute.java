package Logica;

import Control.*;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ControlOfRoute {
    private List<City> cities;
    private List<Route> routes;
    private Graph<City, DefaultWeightedEdge> graph;
    private DijkstraShortestPath<City, DefaultWeightedEdge> dijkstra;
    private GraphPath<City, DefaultWeightedEdge> shortestPath;
    public ControlOfRoute() {
        this.cities = new ArrayList<>(Arrays.asList(
                new City("Bogotá", 4.710989, -74.072092),
                new City("Medellín", 6.244203, -75.581211),
                new City("Cali", 3.451646, -76.531983),
                new City("Barranquilla", 10.963889, -74.796389),
                new City("Cartagena", 10.391049, -75.479426),
                new City("Bucaramanga", 7.129056, -73.125361),
                new City("Cúcuta", 7.893907, -72.50782),
                new City("Santa Marta", 11.240355, -74.211052),
                new City("Pereira", 4.814278, -75.696146),
                new City("Villavicencio", 4.142002, -73.626637)
        ));
        this.routes = new ArrayList<>(Arrays.asList(
                new Route(cities.get(0), cities.get(1), 0),
                new Route(cities.get(0), cities.get(2), 0),
                new Route(cities.get(1), cities.get(2), 0),
                new Route(cities.get(1), cities.get(3), 0),
                new Route(cities.get(2), cities.get(3), 0),
                new Route(cities.get(0), cities.get(3), 0),
                new Route(cities.get(0), cities.get(4), 0),
                new Route(cities.get(1), cities.get(6), 0),
                new Route(cities.get(3), cities.get(7), 0),
                new Route(cities.get(4), cities.get(7), 0),
                new Route(cities.get(0), cities.get(9), 0),
                new Route(cities.get(1), cities.get(9), 0)
        ));
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (City city : cities) {
            graph.addVertex(city);
        }
        for (Route route : routes) {
            graph.addEdge(route.getStartCity(), route.getEndCity());
            graph.setEdgeWeight(graph.getEdge(route.getStartCity(), route.getEndCity()), route.getDistance());
        }
    }
    public String showRoute(int origin, int destiny) {
        this.dijkstra = new DijkstraShortestPath<>(graph);
        this.shortestPath = dijkstra.getPath(cities.get(origin), cities.get(destiny));
        String out = "";

        if (shortestPath != null) {
            out += "La ruta más corta es: " + "\n";
            List<City> shortestRoute = shortestPath.getVertexList();
            double shortestDistance = shortestPath.getWeight();
            for (int i = 0; i < shortestRoute.size() - 1; i++) {
                out += shortestRoute.get(i).getName() + " -> ";
            }
            out += shortestRoute.get(shortestRoute.size() - 1).getName();
            out += "\nLa distancia total de la ruta es: " + shortestDistance;
        } else {
            out += "\nNo se encontró una ruta entre las ciudades especificadas.";
        }
        return out;
    }
}
