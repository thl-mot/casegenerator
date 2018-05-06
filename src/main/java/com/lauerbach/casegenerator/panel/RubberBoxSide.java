package com.lauerbach.casegenerator.panel;
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


import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.svg.Group;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Rectangle;
import com.lauerbach.casegenerator.svg.UnitType;

public class RubberBoxSide extends Panel {

	public static boolean debugPanelOutline = true;
	public static String outlineColor = "yellow";
	public static String debugLineWidth = "0.1mm";

	double border;

	public RubberBoxSide(double w, double h, double teethWidth, double border, UnitType unit, Material m) {
		super(w, h, teethWidth, unit, EdgeType.HOLE, EdgeType.HOLE, EdgeType.HOLE, EdgeType.HOLE, m);
		this.border = border;
	}

	public Group generate(double x, double y, UnitType unit, Drill drill) {
		Drill decorationDrill= new Drill( drill.getDiameterPX(), "blue");

		Group g = super.generate(x + border, y + border, unit, drill, decorationDrill);

		double drillPx = drill.getRadius();

		Point p1 = new Point(x, y, unit);
		Point p2 = new Point(x + w + 2 * border, y + h + 2 * border, unit);

		Rectangle sideRect = new Rectangle(p1.add(-drillPx, -drillPx), p2.add(drillPx, drillPx), drill.getColor(),
				drill.getDiameterPX() + "");
		sideRect.setRx("5mm");
		sideRect.setRy("5mm");
		g.add(sideRect);

		if (debugPanelOutline) {
			Rectangle rect = new Rectangle(p1, p2, outlineColor, debugLineWidth);
			g.add(rect);
		}

		return g;
	}

}
