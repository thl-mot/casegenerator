package com.lauerbach.casegenerator.panel;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.Edge;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.svg.Group;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.UnitType;

public class CollectablesPanel extends Panel {

	double shelves;

	public CollectablesPanel(double w, double h, double noseLength, int shelves, UnitType unit,
			EdgeType leftSide, EdgeType rightSide, EdgeType topSide, EdgeType bottomSide, Material m) {
		super(w, h, noseLength, unit, leftSide, rightSide, topSide, bottomSide, m);
		this.shelves = shelves;
	}

	@Override
	public Group generate(double x, double y, UnitType offsetUnit, Drill drill, Drill decorationDrill) {
		Point offset = new Point(x, y, offsetUnit);
		
		Group g= super.generate(x, y, offsetUnit, drill, decorationDrill);
		
		EdgeFactory shelfTemplate = new EdgeFactory(w, noseLength, 3, UnitType.MM, topMaterial, drill, decorationDrill);
		
		double shelfHeight= h/ (shelves+1);
		for (int i=0; i<shelves; i++) {
			Edge shelfCut = shelfTemplate.getCut(offset.add(0, shelfHeight*(i+1), unit), EdgeType.HOLE, EdgeSide.TOP);
			g.addAll(shelfCut.getDecoration());
		}

		return g;
	}

}
