package com.lauerbach.casegenerator;

import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.panel.CollectablesPanel;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Circle;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class CollectablesBoard {

	double w, h, d;

	int shelves;

	double noseWidth = 15;

	Material frameMaterial, shelfMaterial;

	UnitType unit;

	public CollectablesBoard(double w, double h, double d, int shelfes, UnitType unit, Material frameMaterial,
			Material shelfMaterial) {
		super();
		this.w = w;
		this.h = h;
		this.d = d;
		this.shelves = shelfes;
		this.unit = unit;
		this.frameMaterial = frameMaterial;
		this.shelfMaterial = shelfMaterial;
	}

	public Svg generate(Drill drill, Drill decorationDrill) {

		Svg svg = new Svg("500mm", "600mm");

		double y = 5;
		CollectablesPanel back = new CollectablesPanel(w, h, noseWidth, shelves, unit, EdgeType.NOSE, EdgeType.NOSE,
				EdgeType.NOSECUT, EdgeType.NOSECUT, frameMaterial);
		svg.add(back.generate(5, y, UnitType.MM, drill, decorationDrill));
		svg.add(new Circle(new Point(5 + 20, y + 20, UnitType.MM), 2, UnitType.MM,
				decorationDrill.getColor(), "0.2mm"));
		svg.add(new Circle(new Point(5 + w - 20, y + 20, UnitType.MM), 2, UnitType.MM,
				decorationDrill.getColor(), "0.2mm"));
		y += (h + 5);

		Panel topBottom = new Panel(w, d, noseWidth, unit, EdgeType.NOSE, EdgeType.NOSE, EdgeType.LID, EdgeType.NOSE,
				frameMaterial);
		svg.add(topBottom.generate(5, y, unit, drill, decorationDrill));
		y += (d + 5);
		svg.add(topBottom.generate(5, y, unit, drill, decorationDrill));
		y += (d + 5);
		for (int i = 0; i < shelves; i++) {
			svg.add(topBottom.generate(5, y, unit, drill, decorationDrill));
			y += (d + 5);
		}

		CollectablesPanel sides = new CollectablesPanel(d, h, noseWidth, shelves, unit, EdgeType.LID, EdgeType.NOSECUT,
				EdgeType.NOSECUT, EdgeType.NOSECUT, frameMaterial);
		svg.add(sides.generate(10 + w, 5, unit, drill, decorationDrill));
		svg.add(sides.generate(5 + w + 10 + d, 5, unit, drill, decorationDrill));

		return svg;
	}

}
