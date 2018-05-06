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


import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.svg.Circle;
import com.lauerbach.casegenerator.svg.ContainerElement;
import com.lauerbach.casegenerator.svg.Point;

public class TemplateCircle extends TemplateDecoration {

	double x, y;
	double cutR, outlineR;
	Drill drill = null;

	public TemplateCircle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.outlineR = r;
	}

	@Override
	public void calculateCutPoint(Drill drill) {
		cutR = outlineR - drill.getRadius();
		this.drill = drill;
	}

	public double getX(LineType type) {
		return x;
	}

	public double getY(LineType type) {
		return y;
	}

	public double getR(LineType type) {
		switch (type) {
		case CUT:
			return cutR;
		case OUTLINE:
			return outlineR;
		}
		return 0;
	}

	@Override
	public ContainerElement getTransformedElement(LineType lineType, Point offset, EdgeSide panelSide) {
		Point center = new Point(getX(lineType), getY(lineType));
		switch (panelSide) {
		case TOP:
			return new Circle(center.rotate(0).add(offset), getR(lineType)/2, null, drill.getColor(),
					drill.getDiameterPX() + "");
		case BOTTOM:
			return new Circle(center.rotate(180).add(offset), getR(lineType)/2, null, drill.getColor(),
					drill.getDiameterPX() + "");
		case LEFT:
			return new Circle(center.rotate(270).add(offset), getR(lineType)/2, null, drill.getColor(),
					drill.getDiameterPX() + "");
		case RIGHT:
			return new Circle(center.rotate(90).add(offset), getR(lineType)/2, null, drill.getColor(),
					drill.getDiameterPX() + "");
		}
		return null;
	}

}
