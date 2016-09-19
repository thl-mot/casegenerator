package com.lauerbach.casegenerator;

import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class Cardbox {
	Papersize papersize;
	int length;

	Material material;

	public Cardbox(Papersize papersize, int length, Material material) {
		this.papersize = papersize;
		this.length = length;
		this.material = material;
	}

	public Svg generate(Drill drill, Drill decorationDrill) {
		Svg svg = new Svg("1220mm", "610mm");

		double baseW = papersize.w + 4 * material.getUnitThickness();
		double baseH = papersize.h + 1 * material.getUnitThickness();

		int pos = 5;
		Panel sides = new Panel(length, baseH, 15, UnitType.MM, EdgeType.NOSE, EdgeType.NOSE, EdgeType.LID,
				EdgeType.NOSE, material);
		svg.add(sides.generate(5, pos, UnitType.MM, drill, decorationDrill));
		pos += baseH + 5;

		Panel frontBack = new Panel(baseW, baseH, 15, UnitType.MM, EdgeType.NOSECUT, EdgeType.NOSECUT, EdgeType.LID,
				EdgeType.NOSE, material);
		svg.add(frontBack.generate(5, pos, UnitType.MM, drill, decorationDrill));
		pos += baseH + 5;

		Panel bottom = new Panel(length, baseW, 15, UnitType.MM, EdgeType.NOSECUT, EdgeType.NOSECUT, EdgeType.NOSECUT,
				EdgeType.NOSECUT, material);
		svg.add(bottom.generate(5, pos, UnitType.MM, drill, decorationDrill));
		pos += baseW + 5;

		double lidL = length + 2 * material.getUnitThickness()+1;
		double lidW = baseW + 2 * material.getUnitThickness()+1;
		double lidH = 20;
		System.out.println(baseW + "  " + lidW);

		Panel lidSides = new Panel(lidL, lidH, 10, UnitType.MM, EdgeType.NOSE, EdgeType.NOSE, EdgeType.LID,
				EdgeType.NOSE, material);
		svg.add(lidSides.generate(5, pos, UnitType.MM, drill, decorationDrill));
		pos += lidH + 5;

		Panel lidFrontBack = new Panel(lidW, lidH, 10, UnitType.MM, EdgeType.NOSECUT, EdgeType.NOSECUT, EdgeType.LID,
				EdgeType.NOSE, material);
		svg.add(lidFrontBack.generate(5, pos, UnitType.MM, drill, decorationDrill));
		pos += lidH + 5;

		Panel lid = new Panel(lidL, lidW, 10, UnitType.MM, EdgeType.NOSECUT, EdgeType.NOSECUT, EdgeType.NOSECUT,
				EdgeType.NOSECUT, material);
		svg.add(lid.generate(5, pos, UnitType.MM, drill, decorationDrill));
		pos += lidW + 5;
		
		Panel separator = new Panel(baseW-4, baseH-7, 30, UnitType.MM, EdgeType.LID_CONTACT, EdgeType.LID_CONTACT, EdgeType.LID,
				EdgeType.NOSE, material);
		svg.add(separator.generate(5, pos, UnitType.MM, drill, decorationDrill));
		
		return svg;
	}

}
