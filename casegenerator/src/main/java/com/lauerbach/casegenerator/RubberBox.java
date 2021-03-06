package com.lauerbach.casegenerator;
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


import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.panel.RubberBoxSide;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class RubberBox {
	double l, w, h;
	UnitType unit;

	Material top, bottom, sides, rear, front;

	public RubberBox(double l, double w, double h, UnitType unit, Material m) {
		this.l = l;
		this.w = w;
		this.h = h;
		this.unit = unit;
		this.top = m;
		this.bottom = m;
		this.sides = m;
		this.rear = m;
		this.front = m;
	}

	public Svg generate(Drill drill) {
		Drill decorationDrill= new Drill( drill.getDiameterPX(), "blue");
		
		Svg svg = new Svg("300mm", "300mm");

		Panel top = new Panel(l, w, 15, UnitType.MM, EdgeType.RUBBERNOSE, EdgeType.RUBBERNOSE, EdgeType.NOSE, EdgeType.NOSE,
				sides);
		svg.add(top.generate(5, 5, UnitType.MM,drill, decorationDrill));

		Panel frontBack = new Panel(l, h, 15, UnitType.MM, EdgeType.RUBBERNOSE, EdgeType.RUBBERNOSE, EdgeType.NOSECUT,
				EdgeType.NOSECUT, sides);
		svg.add(frontBack.generate(5, 10 + w, UnitType.MM,drill, decorationDrill));

		RubberBoxSide side = new RubberBoxSide(w, h, 15, 5, UnitType.MM, sides);
		svg.add(side.generate(5, 15 + w + h, UnitType.MM,drill, decorationDrill));

		return svg;
	}

}
