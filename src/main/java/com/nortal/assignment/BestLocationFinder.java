package com.nortal.assignment;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.nortal.assignment.model.Rooftop;
import com.nortal.assignment.model.Superhero;

/**
 * Created by Joosep Lall.
 */
public class BestLocationFinder {

	/**
	 * <ul>
	 * <li>All superheroes can travel straight to any of the rooftops</li>
	 * <li>Best location is the one where the longest distance that any
	 * superhero must travel is the shortest</li>
	 * <li>In case the longest distance is the same for multiple rooftops, use
	 * the shortest distance as a secondary comparator</li>
	 * </ul>
	 * <p>
	 * <b>Example</b> Consider the following example:
	 * http://bit.ly/superherosample We have two possible rooftops and two
	 * superheros. For Rooftop 1 the longest distance that any of the
	 * superheroes must travel is 3 km For Rooftop 2 the longest distance that
	 * any of the superheroes must travel is 5 km Thus Rooftop 1 wins.
	 * <p>
	 * <b>Note:</b> This example used km but you are given a list of special
	 * superhero coordinates. There is no need to convert any units.
	 *
	 * @param rooftops
	 *            List of possible rooftops where superheroes can meet
	 * @param superheroes
	 * @return best Rooftop for cake club meeting
	 */
	public Rooftop findBestLocationForMeeting(List<Rooftop> rooftops, List<Superhero> superheroes) {

		List<Pair> rooftopDistance = new ArrayList<Pair>();

		for (Rooftop rooftop : rooftops) {
			rooftopDistance.add(new Pair(rooftop, 0));
		}

		for (Pair roof : rooftopDistance) {
			for (Rooftop r : rooftops) {
				for (Superhero s : superheroes) {

					if (roof.getRooftop().equals(r)) {
						if (roof.getDistance() < Point2D.distance(s.getxLocation(), s.getyLocation(), r.getxLocation(),
								r.getyLocation()))
							roof.setDistance(Point2D.distance(s.getxLocation(), s.getyLocation(), r.getxLocation(),
									r.getyLocation()));
					}

				}

			}
		}
		Pair bestRooftop=new Pair(rooftops.get(0),1337);
		
		for (Pair roof : rooftopDistance) {
			if(roof.getDistance()<bestRooftop.getDistance()){
				bestRooftop.setRooftop(roof.getRooftop());
				bestRooftop.setDistance(roof.getDistance());
			}
		}
		
		return bestRooftop.getRooftop();
	}

	public class Pair {
		private Rooftop rooftop;
		private double distance;

		public Pair(Rooftop rooftop, double distance) {
			this.rooftop = rooftop;
			this.distance = distance;
		}

		public void setDistance(Double dist) {
			this.distance = dist;
		}
		public void setRooftop(Rooftop rooftop){
			this.rooftop=rooftop;
		}

		public Rooftop getRooftop() {
			return rooftop;
		}

		public double getDistance() {
			return distance;
		}
	}
}
