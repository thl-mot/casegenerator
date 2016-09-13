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


import java.util.ArrayList;
import java.util.List;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.Edge;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.edgegenerator.EdgeTemplateElement.LineType;
import com.lauerbach.casegenerator.edgegenerator.TemplatePoint;
import com.lauerbach.casegenerator.edgegenerator.TemplatePoint.Position;
import com.lauerbach.casegenerator.svg.Group;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Polyline;
import com.lauerbach.casegenerator.svg.Rectangle;
import com.lauerbach.casegenerator.svg.UnitCalculator;
import com.lauerbach.casegenerator.svg.UnitType;

public class MixerBoxSide extends Panel {

	double h1, h2, border;
	
	private double topWidth;

	public MixerBoxSide(double w, double h1, double h2, double teethWidth, double border, UnitType unit, Material m) {
		super(w, Math.min(h1, h2), teethWidth, unit, EdgeType.HOLE, EdgeType.HOLE, EdgeType.HOLE, EdgeType.HOLE, m);
		this.border = border;
		this.h1 = h1;
		this.h2 = Math.max(h2, h1);
	}

	public List<TemplatePoint> getSide(double w, double h1, double h2, double edges, UnitType unit) {
		double pxW = UnitCalculator.getPX(w, unit);
		double pxH1 = UnitCalculator.getPX(h1, unit);
		double pxH2 = UnitCalculator.getPX(h2, unit);
		double pxEdges = UnitCalculator.getPX(edges, unit);

		List<TemplatePoint> l = new ArrayList<>();
		l.add(new TemplatePoint(pxEdges, pxH2 - pxH1, Position.NW));
		l.add(new TemplatePoint(pxW - pxEdges * 2, 0, Position.NW));
		l.add(new TemplatePoint(pxW - pxEdges, 0, Position.NE));
		l.add(new TemplatePoint(pxW, +pxEdges, Position.NE));
		l.add(new TemplatePoint(pxW, pxH2 - pxEdges, Position.SE));
		l.add(new TemplatePoint(pxW - pxEdges, pxH2, Position.SE));
		l.add(new TemplatePoint(0 + pxEdges, pxH2, Position.SW));
		l.add(new TemplatePoint(0, pxH2 - pxEdges, Position.SW));
		l.add(new TemplatePoint(0, pxH2 - pxH1 + pxEdges, Position.NW));
		l.add(new TemplatePoint(pxEdges, pxH2 - pxH1, Position.NW));
		return l;
	}

	public Group generate(double x, double y, UnitType offsetUnit, Drill drill, Drill decorationDrill) {

		Point baseOffset = new Point(x, y, offsetUnit);
		Point sideOffset = baseOffset.add(border, border, unit);

		// double drillPx = drill.getRadius();
		// double borderPx = UnitCalculator.getPX(border, unit);
		
		Group g = new Group();

		List<TemplatePoint> outlineTemplate = getSide(w + border * 2, h1 + border * 2, h2 + border * 2, 5, offsetUnit);

		Polyline sideCut = new Polyline(drill.getColor(), drill.getDiameterPX() + "");
		sideCut.addAll(outlineTemplate, LineType.CUT, baseOffset, drill);
		g.add(sideCut);

		if (debugPanelOutline) {
			Rectangle outline = new Rectangle(baseOffset, baseOffset.add(w + border * 2, h2 + border * 2, unit),
					"yellow", debugLineWidth);
			g.add(outline);

			Polyline sideOutline = new Polyline(outlineColor, debugLineWidth);
			sideOutline.addAll(outlineTemplate, LineType.OUTLINE, baseOffset, drill);
			g.add(sideOutline);

			Polyline frame = new Polyline(outlineColor, debugLineWidth);
			frame.addPoint(new Point(0, (h2 - h1), unit).add(sideOffset));
			frame.addPoint(new Point(w, 0, unit).add(sideOffset));
			frame.addPoint(new Point(w, h2, unit).add(sideOffset));
			frame.addPoint(new Point(0, h2, unit).add(sideOffset));
			frame.addPoint(new Point(0, (h2 - h1), unit).add(sideOffset));
			g.add(frame);

		}

		this.topWidth = Math.sqrt((h2 - h1) * (h2 - h1) + w * w);
		double alpha = Math.acos((h2 - h1) / topWidth);

		EdgeFactory topEdgeTemplate = new EdgeFactory(topWidth, noseLength, 3, UnitType.MM, topMaterial, drill, decorationDrill);
		EdgeFactory bottomEdgeTemplate = new EdgeFactory(w, noseLength, 3, UnitType.MM, bottomMaterial, drill, decorationDrill);
		EdgeFactory rightEdgeTemplate = new EdgeFactory(h2, noseLength, 3, UnitType.MM, rightMaterial, drill, decorationDrill);
		EdgeFactory leftEdgeTemplate = new EdgeFactory(h1, noseLength, 3, UnitType.MM, leftMaterial, drill, decorationDrill);

		Edge topCut = topEdgeTemplate.getCut(new Point(0,0), topSide, EdgeSide.TOP);
		Edge bottomCut = bottomEdgeTemplate.getCut(sideOffset.add(0, h2, this.unit), bottomSide, EdgeSide.BOTTOM);
		Edge leftCut = leftEdgeTemplate.getCut(sideOffset.add(0, (h2 - h1), this.unit), leftSide, EdgeSide.LEFT);
		Edge rightCut = rightEdgeTemplate.getCut(sideOffset.add(w, 0, this.unit), rightSide, EdgeSide.RIGHT);

		Group topGroup = new Group();
		topGroup.addAll(topCut.getDecoration());
		topGroup.addTranslate(sideOffset.add(0, (h2-h1), unit));
		topGroup.addRotate(alpha * 180 / Math.PI - 90, new Point(0, 0));

		g.add(topGroup);

		g.addAll(rightCut.getDecoration());
		g.addAll(leftCut.getDecoration());
		g.addAll(bottomCut.getDecoration());

		return g;
	}

	public double getTopWidth() {
		return topWidth;
	}
	
}
