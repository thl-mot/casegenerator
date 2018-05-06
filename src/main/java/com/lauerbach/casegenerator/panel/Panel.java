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
import com.lauerbach.casegenerator.PanelDecorator;
import com.lauerbach.casegenerator.edgegenerator.Edge;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.svg.Group;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Polyline;
import com.lauerbach.casegenerator.svg.Rectangle;
import com.lauerbach.casegenerator.svg.UnitType;

public class Panel {

	public static boolean debugPanelOutline = false;
	public static boolean debugPanelResult = false;

	public static String outlineColor = "green";
	public static String resultColor = "orange";
	public static String debugLineWidth = "0.1mm";

	double w, h;
	double noseLength;
	UnitType unit;

	Material leftMaterial, rightMaterial, topMaterial, bottomMaterial;
	EdgeType leftSide, rightSide, topSide, bottomSide;
	
	PanelDecorator panelDecorator= null;

	public Panel(double w, double h, double noseLength, UnitType unit, EdgeType leftSide, EdgeType rightSide,
			EdgeType topSide, EdgeType bottomSide, Material leftMaterial, Material rightMaterial, Material topMaterial, Material bottomMaterial) {
		this.w = w;
		this.h = h;
		this.unit = unit;
		this.noseLength = noseLength;

		this.leftSide = leftSide;
		this.rightSide = rightSide;
		this.topSide = topSide;
		this.bottomSide = bottomSide;

		this.leftMaterial = leftMaterial;
		this.rightMaterial = rightMaterial;
		this.topMaterial = topMaterial;
		this.bottomMaterial = bottomMaterial;
	}
	
	public Panel(double w, double h, double noseLength, UnitType unit, EdgeType leftSide, EdgeType rightSide,
			EdgeType topSide, EdgeType bottomSide, Material m) {
		this( w,h,noseLength, unit, leftSide, rightSide, topSide, bottomSide, m,m,m,m);
	}

	public Point getLast(List<Point> l) {
		if (l.size() > 0) {
			return l.get(l.size() - 1);
		} else {
			return null;
		}
	}

	public List<Point> merge(List<Point> l1, List<Point> l2, List<Point> l3, List<Point> l4) {
		List<Point> result = new ArrayList<>();

		if (l1.size() > 1 && l2.size() > 1 && l3.size() > 1 && l4.size() > 1) {
			getLast(l1).setX(l2.get(0).getX());
			getLast(l2).setY(l3.get(0).getY());
			getLast(l3).setX(l4.get(0).getX());

			getLast(l4).setY(l1.get(0).getY());

			l1.get(0).setX(getLast(l4).getX());

			result.addAll(l1);
			result.addAll(l2.subList(1, l2.size()));
			result.addAll(l3.subList(1, l3.size()));
			result.addAll(l4.subList(1, l4.size()));
		}

		return result;
	}

	public Group generate(double x, double y, UnitType offsetUnit, Drill drill, Drill decorationDrill) {
		Group g = new Group();

		Polyline cut = new Polyline(drill.getColor(), drill.getDiameterPX() + "");

		EdgeFactory topEdgeTemplate = new EdgeFactory(w, noseLength, 3, UnitType.MM, topMaterial, drill,
				decorationDrill);
		EdgeFactory bottomEdgeTemplate = new EdgeFactory(w, noseLength, 3, UnitType.MM, bottomMaterial, drill,
				decorationDrill);
		EdgeFactory rightEdgeTemplate = new EdgeFactory(h, noseLength, 3, UnitType.MM, rightMaterial, drill,
				decorationDrill);
		EdgeFactory leftEdgeTemplate = new EdgeFactory(h, noseLength, 3, UnitType.MM, leftMaterial, drill,
				decorationDrill);

		Point offset = new Point(x, y, offsetUnit);

		System.out.println(offset.getX() + " " + offset.getY());

		Edge topCut = topEdgeTemplate.getCut(offset.add(0, 0, this.unit), topSide, EdgeSide.TOP);
		Edge bottomCut = bottomEdgeTemplate.getCut(offset.add(0, h, this.unit), bottomSide, EdgeSide.BOTTOM);
		Edge leftCut = leftEdgeTemplate.getCut(offset.add(0, 0, this.unit), leftSide, EdgeSide.LEFT);
		Edge rightCut = rightEdgeTemplate.getCut(offset.add(w, 0, this.unit), rightSide, EdgeSide.RIGHT);

		cut.addAll(merge(topCut.getOutline(), rightCut.getOutline(), bottomCut.getOutline(), leftCut.getOutline()));
		g.add(cut);
		g.addAll(topCut.getDecoration());
		g.addAll(bottomCut.getDecoration());
		g.addAll(leftCut.getDecoration());
		g.addAll(rightCut.getDecoration());

		if (debugPanelResult) {
			Polyline matOutline = new Polyline(resultColor, debugLineWidth);

			Edge topMat = topEdgeTemplate.getOutline(offset.add(0, 0, this.unit), topSide, EdgeSide.TOP);
			Edge bottomMat = bottomEdgeTemplate.getOutline(offset.add(0, h, this.unit), bottomSide, EdgeSide.BOTTOM);
			Edge leftMat = leftEdgeTemplate.getOutline(offset.add(0, 0, this.unit), leftSide, EdgeSide.LEFT);
			Edge rightMat = rightEdgeTemplate.getOutline(offset.add(w, 0, this.unit), rightSide, EdgeSide.RIGHT);

			matOutline.addAll(
					merge(topMat.getOutline(), rightMat.getOutline(), bottomMat.getOutline(), leftMat.getOutline()));
			g.add(matOutline);
		}

		if (debugPanelOutline) {
			Rectangle rect = new Rectangle(new Point(x, y, offsetUnit), new Point(x + w, y + h, offsetUnit),
					outlineColor, debugLineWidth);
			g.add(rect);
		}

		if (panelDecorator!=null) {
			Group deco= new Group();
			deco.addTranslate( offset);
			panelDecorator.addDecoration( deco, decorationDrill);
			g.add( deco);
		}
		return g;
	}

	public PanelDecorator getPanelDecorator() {
		return panelDecorator;
	}

	public void setPanelDecorator(PanelDecorator panelDecorator) {
		this.panelDecorator = panelDecorator;
	}

}
