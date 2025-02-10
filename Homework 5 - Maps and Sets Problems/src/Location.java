// DO NOT CHANGE THIS FILE
public class Location {
	String zip;
	String city;
	String stateCode;
	String state;
	int population;
	double density;
	int countyCode;
	String county;
	String timeZone;
	
	Location(String[] tokens) {
		zip = tokens[0];
		city = tokens[1];
		stateCode = tokens[2];
		state = tokens[3];
		population = Integer.parseInt(tokens[4]);
		density = Double.parseDouble(tokens[5]);
		countyCode = Integer.parseInt(tokens[6]);
		county = tokens[7];
		timeZone = tokens[8];
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %d %.1f %s%n", zip, city, county, state, population, density, timeZone);
	}
}
