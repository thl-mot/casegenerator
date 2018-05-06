package com.lauerbach.casegenerator;

import com.lauerbach.casegenerator.edgegenerator.Edge;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.edgegenerator.EdgeTemplateElement.LineType;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Circle;
import com.lauerbach.casegenerator.svg.Group;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Polyline;
import com.lauerbach.casegenerator.svg.Rectangle;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class CableRoller {
	double innerR, outerR, width;
	UnitType unit;

	Material material;
	private double triangleH;

	public CableRoller(double width, double innerR, double outerR, UnitType unit, Material m) {
		this.width = width;
		this.innerR = innerR;
		this.outerR = outerR;
		this.unit = unit;
		this.material = m;

		triangleH = Math.sqrt(innerR * innerR - (innerR * innerR / 4));

	}

	public Group generateSides(Point offset, Drill drill, Drill decoDrill) {
		Group sideGroup = new Group();

		Point center = new Point(outerR, outerR, unit);

		sideGroup.add(new Circle(center, outerR, unit, drill.getColor(), drill.getDiameterPX() + ""));
		sideGroup.add(new Circle(center, 5, UnitType.MM, drill.getColor(), decoDrill.getDiameterPX() + ""));

		EdgeFactory e = new EdgeFactory(innerR, innerR / 2, 0, unit, material, decoDrill, decoDrill);
		Edge edge = e.getEdge(LineType.CUT, center.add(triangleH, -innerR / 2, unit), EdgeType.HOLE, EdgeSide.RIGHT);

		for (int i = 0; i < 6; i++) {
			Group g = new Group();

			Polyline polyline = new Polyline("green", "0.1");
			polyline.addPoint(center.add(triangleH, -innerR / 2, unit));
			polyline.addPoint(center.add(triangleH, innerR / 2, unit));
			g.add(polyline);

			polyline = new Polyline("green", "0.1");
			polyline.addPoint(center.add(triangleH, -innerR / 2, unit).add(-material.getPxThickness(), 0));
			polyline.addPoint(center.add(triangleH, innerR / 2, unit).add(-material.getPxThickness(), 0));
			g.add(polyline);

			g.addAll(edge.getDecoration());
			g.addRotate(60 * i, center);

			sideGroup.add(g);
		}
		sideGroup.add(new Circle(center.add(innerR, 0, UnitType.MM), 2, UnitType.MM, decoDrill.getColor(),
				decoDrill.getDiameterPX() + ""));
		
		Point p1= center.add( outerR-10, -1.5, UnitType.MM);
		Point p2= center.add( outerR, 1.5, UnitType.MM);
		sideGroup.add( new Rectangle(p1, p2, decoDrill.getColor(), decoDrill.getStroke()));

		sideGroup.add(new Circle( center.add( outerR-20, 0, UnitType.MM), 2, UnitType.MM, decoDrill.getColor(),
				decoDrill.getDiameterPX() + ""));
		
		sideGroup.addTranslate(offset);
		return sideGroup;
	}

	public Group generateDistance(Point offset, double cableDrill, Drill drill, Drill decoDrill) {
		Group drumGroup = new Group();
		Panel panel = new Panel(innerR, width, innerR / 2, unit, EdgeType.NOSE, EdgeType.NOSECUT, EdgeType.RUBBERNOSE,
				EdgeType.RUBBERNOSE, material);
		drumGroup.add(panel.generate(0, 0, UnitType.MM, drill, drill));
		drumGroup.add(new Circle(new Point(innerR / 2, cableDrill, unit).add(0, material.getPxThickness()), cableDrill,
				UnitType.MM, decoDrill.color, "0.2mm"));
		drumGroup.addTranslate(offset);
		return drumGroup;
	}

	public Svg generate(Drill drill, Drill decoDrill) {
		Svg svg = new Svg("1220mm", "610mm");
		svg.add(generateSides(new Point(5, 5, unit), drill, decoDrill));
		svg.add(generateSides(new Point(5, outerR * 2 + 10, unit), drill, decoDrill));

		for (int i = 0; i < 6; i++) {
			int r = i / 3;
			int c = i % 3;
			svg.add(generateDistance(new Point((c * (innerR + 5)) + 5, (outerR + 5) * 4 + (width + 20) * r + 10, unit),
					1 + i, drill, decoDrill));
		}
		return svg;
	}

}
