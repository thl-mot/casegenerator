package com.lauerbach.casegenerator.edgegenerator;
/**
 * Copyright 2016 Thomas Lauerbach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.EdgeTemplateElement.LineType;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.UnitCalculator;
import com.lauerbach.casegenerator.svg.UnitType;

public class EdgeFactory {

	public enum EdgeType {
		NOSECUT, NOSE, NOSE_WITH_HOLE, T_CUT, RUBBERNOSE, HOLE, LID, LID_CONTACT
	}

	public enum EdgeSide {
		TOP, BOTTOM, RIGHT, LEFT
	}

	double length;
	double noseLength;
	double gap;
	Material material;
	UnitType unit;
	Drill drill;
	Drill decorationDrills;

	public EdgeFactory(double length, double noseLength, double gap, UnitType unit, Material material, Drill drill,
			Drill decoDrill) {
		super();
		this.length = length;
		this.noseLength = noseLength;
		this.material = material;
		this.unit = unit;
		this.drill = drill;
		this.decorationDrills = decoDrill;
		this.gap= gap;
	}

	public Edge getEdge(LineType lineType, Point pos, EdgeType type, EdgeSide panelSide) {
		Edge edge = new Edge();

		List<EdgeTemplateElement> l = calculateCut(type);
		Iterator<EdgeTemplateElement> i = l.iterator();
		while (i.hasNext()) {
			EdgeTemplateElement e = i.next();
			if (e instanceof TemplatePoint) {
				TemplatePoint tp = (TemplatePoint) e;
				tp.calculateCutPoint(drill);
				Point p = tp.getTransformedPoint(lineType, pos, panelSide);
				edge.addPoint(p);
			} else {
				TemplateDecoration decoration = (TemplateDecoration) e;
				decoration.calculateCutPoint(decorationDrills);
				edge.addDecoration(decoration.getTransformedElement(lineType, pos, panelSide));
			}
		}

		if (panelSide == EdgeSide.BOTTOM || panelSide == EdgeSide.LEFT) {
			// Brings all collections in correct order, to draw clockwise
			edge.reverse();
		}
		return edge;
	}

	/**
	 * This method turns the basic edge layout so it can be used for a panel on
	 * the given side.
	 * 
	 * @param type
	 * @return
	 */
	public Edge getCut(Point pos, EdgeType type, EdgeSide panelSide) {
		return getEdge(LineType.CUT, pos, type, panelSide);
	}

	public Edge getOutline(Point pos, EdgeType type, EdgeSide panelSide) {
		return getEdge(LineType.OUTLINE, pos, type, panelSide);
	}

	/**
	 * This method creates the basic layout of the edge, by the given EdgeType.
	 * it is equivalent to create the top side of a panel. Material is under the
	 * line
	 * 
	 * @param type
	 * @return
	 */
	private List<EdgeTemplateElement> calculateCut(EdgeType type) {
		NoseGenerator gen = null;
		switch (type) {
		case NOSECUT:
			gen = new NoseCut(noseLength, unit, material);
			break;
		case NOSE:
			gen = new Nose(noseLength, unit, material);
			break;
		case LID:
			gen = new Lid(material);
			break;
		case LID_CONTACT:
			gen = new LidContact(material);
			break;
		case T_CUT:
			gen = new NoseTCut(noseLength, unit, material, 3, 20);
			break;
		case RUBBERNOSE:
			gen = new RubberNose(noseLength, 2, unit, material);
			break;
		case NOSE_WITH_HOLE:
			gen = new NoseWithScrew(noseLength, unit, material, Screw.getScrew(3, 15));
			break;
		case HOLE:
			gen = new Hole(10, noseLength, unit, material);
			break;
		default:
		}
		return calculate(gen);
	}

	/**
	 * This method creates the basic layout of the edge, by the given
	 * nosegenerator
	 * 
	 * @param noseGenerator
	 * @return
	 */
	private List<EdgeTemplateElement> calculate(NoseGenerator noseGenerator) {
		List<EdgeTemplateElement> l = new ArrayList<>();

		int count = (int) ((length-gap*2) / noseLength);
		if (count == 0) {
			return l;
		}
		if (count % 2 == 0 && count >= 2) {
			count--;
		}
		int noseCount = (count - 1) / 2;

		double gap = (length - (noseLength * (count))) / 2;
		// if (count % 2 == 0) {
		// noseCount= count/2;
		// } else {
		// noseCount= (count-1)/2;
		// }

		noseGenerator.addStart(l, 0);

		for (int i = 0; i <= noseCount; i++) {
			double px = UnitCalculator.getPX(gap + (i * 2) * noseLength, unit);
			noseGenerator.addNose(l, px, i + 1, noseCount);
		}

		double px = UnitCalculator.getPX(length, unit);
		noseGenerator.addEnd(l, px);

		return l;
	}

}
