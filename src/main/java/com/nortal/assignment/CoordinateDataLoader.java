package com.nortal.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.nortal.assignment.model.Rooftop;
import com.nortal.assignment.model.Superhero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Joosep Lall.
 */
public class CoordinateDataLoader {
	private static final Logger LOG = LoggerFactory.getLogger(CoordinateDataLoader.class);
	private Reader reader;
	private BufferedReader br;
	private List<Rooftop> rooftops = new ArrayList<>();
	private List<Superhero> superheroes = new ArrayList<>();
	private int superheroeCount;

	public CoordinateDataLoader() {
	}

	/**
	 * Read superhero and rooftop coordinate data. Input file is expected to
	 * have the following format
	 * <ul>
	 * <li>First line contains single numeric value (n) - count of superheroes
	 * </li>
	 * <li>Following n lines contain superhero data: name;x;y</li>
	 * <li>Rest of the lines contain rooftops data: building name;x;y</li>
	 * </ul>
	 *
	 * <b>Sample:</b> 2 Superman;10;10 Batman;45;23 Central Perk;25;33
	 * Moe's;44;30
	 * 
	 * @throws IOException
	 *
	 */
	public void readDataFromFile(InputStream inputStream) {
		try {
			reader = new InputStreamReader(inputStream);
			br = new BufferedReader(reader);
			String line;
			String[] temp;
			while ((line = br.readLine()) != null) {

				if (line.contains(";")) {
					temp = (line.split(";"));
					if (superheroes.size() != superheroeCount) {
						superheroes
								.add(new Superhero(temp[0], Double.parseDouble(temp[1]), Double.parseDouble(temp[2])));
					} else if (superheroes.size() >= superheroeCount) {
						rooftops.add(new Rooftop(temp[0], Double.parseDouble(temp[1]), Double.parseDouble(temp[2])));
					}
				} else {
					superheroeCount = Integer.parseInt(line);
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				inputStream.close();
				reader.close();
				br.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}

		LOG.info("{} superheroes and {} rooftops read from stream.", superheroes.size(), rooftops.size());
	}

	public List<Rooftop> getRooftops() {
		return rooftops;
	}

	public List<Superhero> getSuperheroes() {
		return superheroes;
	}
}
