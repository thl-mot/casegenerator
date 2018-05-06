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
import com.lauerbach.casegenerator.panel.MixerBoxSide;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class MixerBox implements SvgGenerator {
	double l, w, h1, h2;
	UnitType unit;

	Material top, bottom, sides, rear, front;

	public MixerBox(double l, double w, double h1, double h2, UnitType unit, Material m) {
		this.l = l;
		this.w = w;
		this.h1 = h1;
		this.h2 = h2;
		this.unit = unit;
		this.top = m;
		this.bottom = m;
		this.sides = m;
		this.rear = m;
		this.front = m;
	}

	public Svg generate(Drill drill, Drill decoDrill) {
		Svg svg = new Svg("300mm", "300mm");

		double y= 5;
		
		MixerBoxSide side = new MixerBoxSide(w, h1, h2, 15, 5, UnitType.MM, new Material(3, UnitType.MM));

		svg.add(side.generate(5, y, UnitType.MM, drill, decoDrill));
		svg.add(side.generate(5+w+20, y, UnitType.MM, drill, decoDrill));
		
		y= y+ h2 + 5 + 10 ;

		Panel top = new Panel(l, side.getTopWidth(), 15, UnitType.MM, EdgeType.RUBBERNOSE, EdgeType.RUBBERNOSE, EdgeType.NOSE,
				EdgeType.NOSE, sides);

		svg.add(top.generate(5, y, UnitType.MM, drill, drill));
		y= y+ side.getTopWidth() + 5;

		Panel bottom = new Panel(l, w, 15, UnitType.MM, EdgeType.RUBBERNOSE, EdgeType.RUBBERNOSE, EdgeType.NOSE,
				EdgeType.NOSE, sides);

		svg.add(bottom.generate(5, y, UnitType.MM, drill, drill));
		y= y+ w + 5;
		
		Panel back = new Panel(l, h2, 15, UnitType.MM, EdgeType.RUBBERNOSE, EdgeType.RUBBERNOSE, EdgeType.NOSECUT,
				EdgeType.NOSECUT, sides);
		svg.add(back.generate(5, y, UnitType.MM, drill, drill));
		y= y+ h2 + 5;

		Panel front = new Panel(l, h1, 15, UnitType.MM, EdgeType.RUBBERNOSE, EdgeType.RUBBERNOSE, EdgeType.NOSECUT,
				EdgeType.NOSECUT, sides);
		svg.add(front.generate(5, y, UnitType.MM, drill, drill));

		return svg;
	}

}
