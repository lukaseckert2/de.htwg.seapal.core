package de.htwg.seapal.model;

import java.util.ArrayList;
import java.util.List;

public interface IRace extends IModel {
	/**
	 * Gets the race name.
	 * @return The race name.
	 */
	String getName();
	
	/**
	 * Sets the race name.
	 * @param name The race name.
	 */
	void setName(String name);
	
	/**
	 * Gets the name of the boat class.
	 * @return The name of the boat class.
	 */
	String getBoatClass();
	
	/**
	 * Sets the name of the boat class.
	 * @param boatClass The name of the boat class.
	 */
	void setBoatClass(String boatClass);
	
	/**
	 * Gets the trips.
	 * @return The trips.
	 */
	List<RaceTrip> getTrips();
	
	/**
	 * Sets the trips.
	 * @param trips The trips.
	 */
	void setTrips(List<RaceTrip> trips);
	
	/**
	 * Gets the control points
	 * @return The control points.
	 */
	List<RaceControlPoint> getControlPoints();
	
	/**
	 * Sets the control points.
	 * @param controlPoints The control points.
	 */
	void setControlPoints(List<RaceControlPoint> controlPoints);
	
	/**
	 * The class of an inner trip.
	 * @author Benjamin
	 */
	public static class RaceTrip {
		public String id;
		public String name;
		public RaceBoat boat;
		public List<RaceWaypoint> waypoints = new ArrayList<RaceWaypoint>();
		
		public RaceTrip() { }
		
		public RaceTrip(String id, String name, RaceBoat boat, List<RaceWaypoint> waypoints) {
			this.id = id;
			this.name = name;
			this.boat = boat;
			this.waypoints = waypoints;
		}
	}
	
	/**
	 * The class of an inner boat.
	 * @author Benjamin
	 */
	public static class RaceBoat {
		public String id;
		public String name;
		public String IOCCode;
		
		public RaceBoat() { }
		
		public RaceBoat(String id, String name, String iocCode) {
			this.id = id;
			this.name = name;
			this.IOCCode = iocCode;
		}
	}
	
	/**
	 * The class of an inner waypoint.
	 * @author Benjamin
	 */
	public static class RaceWaypoint {
		public String id;
		public RaceCoordinate coord;
		public Long timestamp;
		public Integer sog;
		public String markPassing;
		
		public RaceWaypoint() { }
		
		public RaceWaypoint(String id, RaceCoordinate coord, Long timestamp, Integer sog, String markPassing) {
			this.id = id;
			this.coord = coord;
			this.timestamp = timestamp;
			this.sog = sog;
			this.markPassing = markPassing;
		}
	}
	
	/**
	 * The class of an inner control point.
	 * <p>
	 * Note: Start and goal have two coordinates, simple courese marks just one.
	 * </p>
	 * @author Benjamin
	 */
	public static class RaceControlPoint {
		public String id;
		public List<RaceCoordinate> coords = new ArrayList<RaceCoordinate>();
	
		public RaceControlPoint() { }
		
		public RaceControlPoint(String id, List<RaceCoordinate> coords) {
			this.id = id;
			this.coords = coords;
		}
	}
	
	/**
	 * The class of an inner coordiante.
	 * @author Benjamin
	 */
	public static class RaceCoordinate {
		public Double lat;
		public Double lng;
		
		public RaceCoordinate() { }
		
		public RaceCoordinate(Double lat, Double lng) {
			this.lat = lat;
			this.lng = lng;
		}
	}
}
