package casegenerator;
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

import java.io.File;

import org.junit.Test;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Polyline;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class TestEdge {

	private Polyline getBorder(double x, double y, double width, double noseLength) {
		Material m = new Material(3, UnitType.MM);
		Drill drill = new Drill(0.2, UnitType.MM, "red");
		Polyline l1 = new Polyline(drill.getColor(), drill.getDiameterPX() + "");

		EdgeFactory horEdgeTemplate = new EdgeFactory(width, noseLength, 3, UnitType.MM, m, drill, drill);
		l1.addAll(horEdgeTemplate.getCut(new Point(x, y, UnitType.MM), EdgeType.NOSE, EdgeSide.TOP).getOutline());
		return l1;
	}

	@Test
	public void testGap() {
		Svg svg = new Svg("200mm", "200mm");
		svg.add(getBorder(5, 5, 30, 20));
		svg.add(getBorder(5, 15, 40, 20));
		svg.add(getBorder(5, 25, 50, 20));
		svg.add(getBorder(5, 35, 60, 20));
		svg.add(getBorder(5, 45, 70, 20));
		svg.add(getBorder(5, 55, 80, 20));

		svg.write(new File("test.svg"), true);

	}

	// @Test
	public void test() {
		Panel.debugPanelResult = true;
		Panel.debugPanelOutline = true;

		Material m = new Material(5, UnitType.MM);
		Drill drill = new Drill(0.2, UnitType.MM, "red");

		EdgeFactory horEdgeTemplate = new EdgeFactory(180, 20, 3, UnitType.MM, m, drill, drill);

		Polyline l1 = new Polyline("red", drill.getDiameterPX() + "");
		Polyline l2 = new Polyline("red", drill.getDiameterPX() + "");
		Polyline l3 = new Polyline("red", drill.getDiameterPX() + "");
		Polyline l4 = new Polyline("red", drill.getDiameterPX() + "");
		Polyline l5 = new Polyline("red", drill.getDiameterPX() + "");

		l1.addAll(horEdgeTemplate.getCut(new Point(10, 10, UnitType.MM), EdgeType.T_CUT, EdgeSide.TOP).getOutline());
		l2.addAll(
				horEdgeTemplate.getCut(new Point(10, 10 + 25, UnitType.MM), EdgeType.NOSE, EdgeSide.TOP).getOutline());
		l3.addAll(horEdgeTemplate.getCut(new Point(10, 10 + 40, UnitType.MM), EdgeType.RUBBERNOSE, EdgeSide.TOP)
				.getOutline());
		l4.addAll(horEdgeTemplate.getCut(new Point(10, 10 + 55, UnitType.MM), EdgeType.NOSE_WITH_HOLE, EdgeSide.TOP)
				.getOutline());
		l5.addAll(
				horEdgeTemplate.getCut(new Point(10, 10 + 70, UnitType.MM), EdgeType.HOLE, EdgeSide.TOP).getOutline());

		Svg svg = new Svg("200mm", "200mm");
		svg.add(l1);
		svg.add(l2);
		svg.add(l3);
		svg.add(l4);
		svg.add(l5);
		svg.addAll(horEdgeTemplate.getCut(new Point(10, 10 + 55, UnitType.MM), EdgeType.NOSE_WITH_HOLE, EdgeSide.TOP)
				.getDecoration());
		svg.addAll(horEdgeTemplate.getCut(new Point(10, 10 + 70, UnitType.MM), EdgeType.HOLE, EdgeSide.TOP)
				.getDecoration());
		svg.write(new File("results/testEdges.svg"), true);

	}

}
