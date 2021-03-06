package com.lauerbach.casegenerator.edgegenerator;
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

import java.util.List;

import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.TemplatePoint.Position;
import com.lauerbach.casegenerator.svg.UnitCalculator;
import com.lauerbach.casegenerator.svg.UnitType;

public class NoseWithScrew implements NoseGenerator {

	double length; // px
	double thickness; // px
	Screw screw;

	public NoseWithScrew(double noseLength, double thicknessPX, Screw screw) {
		this.length = noseLength;
		this.thickness = thicknessPX;
		this.screw = screw;
	}

	public NoseWithScrew(double noseLength, UnitType unit, Material material, Screw screw) {
		this.length = UnitCalculator.getPX(noseLength, unit);
		this.thickness = material.getPxThickness();
		this.screw = screw;
	}

	@Override
	public void addNose(List<EdgeTemplateElement> list, double pos, int noseIdx, int noseCount) {
		list.add(new TemplatePoint(pos, thickness, Position.NW));
		list.add(new TemplatePoint(pos, 0, Position.NW));
		list.add(new TemplatePoint(pos + length, 0, Position.NE));
		list.add(new TemplatePoint(pos + length, thickness, Position.NE));
		if (noseIdx == 1 || noseIdx == noseCount) {
			list.add(new TemplateCircle(pos + length / 2, thickness / 2, screw.getM()));
		}
	}

	@Override
	public void addStart(List<EdgeTemplateElement> list, double pos) {
		list.add(new TemplatePoint(pos, thickness, Position.NW));
	}

	@Override
	public void addEnd(List<EdgeTemplateElement> list, double pos) {
		list.add(new TemplatePoint(pos, thickness, Position.NE));
	}

}
