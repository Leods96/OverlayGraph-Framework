package graph_hopper;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.routing.util.EncodingManager;
import objects.Point;

import static controllers.Controller.graphPath;

public class GraphHopperInstance {

    private static final String WORK_DIR = "C:\\Users\\leo\\Desktop\\ThesisProject1.0\\Graph";
    private static final String VEHICLE = "car";

    private GraphHopper graphHopper;

    /**
     * Create the graph or load it from the memory
     */
    public void preprocessing(String osmPath) {
        graphHopper = new GraphHopper().setGraphHopperLocation(graphPath + "\\graphhopper") // "gh-car"
                .setEncodingManager(new EncodingManager(VEHICLE)) // "car"
                .setOSMFile(osmPath)
                .forServer();
        System.out.println("Creation or Load of the graph hopper graph..");
        graphHopper.importOrLoad();
        System.out.println("graph hopper ready");
    }

    /**
     * Make the route between origin and destination
     * @param origin array with geo_coordinate of the origin location
     * @param destination array with geo_coordinate of the destination location
     * @return PathWrapper object with the data of the route
     */
    public PathWrapper routing(double[] origin, double[] destination){
        GHRequest request = new GHRequest(origin[0], origin[1], destination[0], destination[1]);

        request.setWeighting("fastest");
        request.setVehicle(VEHICLE); // "car"
        GHResponse route = graphHopper.route(request);
        return route.getBest();
    }

    public PathWrapper routing(Point origin, Point destination){
        GHRequest request = new GHRequest(origin.getLatitude(), origin.getLongitude(),
                destination.getLatitude(), destination.getLongitude());

        request.setWeighting("fastest");
        request.setVehicle(VEHICLE); // "car"
        GHResponse route = graphHopper.route(request);
        return route.getBest();
    }

}
