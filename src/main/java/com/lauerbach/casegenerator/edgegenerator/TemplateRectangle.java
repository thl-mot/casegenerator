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
import com.lauerbach.casegenerator.svg.ContainerElement;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Rectangle;

public class TemplateRectangle extends TemplateDecoration {

	double x1, y1, x2, y2;
	double cutX1, cutY1, cutX2, cutY2;
	Drill drill = null;

	public TemplateRectangle(double x1, double y1, double x2, double y2) {
		this.x1= x1;
		this.y1= y1;
		this.x2= x2;
		this.y2= y2;
	}
	
	@Override
	public void calculateCutPoint(Drill drill) {
		cutX1= x1+drill.getRadius();
		cutY1= y1+drill.getRadius();
		cutX2= x2-drill.getRadius();
		cutY2= y2-drill.getRadius();
		this.drill= drill;
	}

	public Point getPoint1(LineType type) {
		switch (type) {
		case CUT:
			return new Point( cutX1, cutY1);
		case OUTLINE:
			return new Point( x1, y1);
		}
		return null;
	}

	public Point getPoint2(LineType type) {
		switch (type) {
		case CUT:
			return new Point( cutX2, cutY2);
		case OUTLINE:
			return new Point( x2, y2);
		}
		return null;
	}

	@Override
	public double getX(LineType type) {
		switch (type) {
		case CUT:
			return cutX1;
		case OUTLINE:
			return x1;
		}
		return 0;
	}

	@Override
	public double getY(LineType type) {
		switch (type) {
		case CUT:
			return cutY1;
		case OUTLINE:
			return y1;
		}
		return 0;
	}

	@Override
	public ContainerElement getTransformedElement(LineType lineType, Point offset, EdgeSide panelSide) {
		int angleClockwise=0;
		switch (panelSide) {
		case TOP:
			angleClockwise=0;
			break;
		case BOTTOM:
			angleClockwise= 180;
			break;
		case LEFT:
			angleClockwise= 270;
			break;
		case RIGHT:
			angleClockwise=90;
		}

		Point p1= getPoint1( lineType).rotate(angleClockwise).add(offset);
		Point p2= getPoint2( lineType).rotate(angleClockwise).add(offset);
		return new Rectangle(p1, p2, drill.getColor(), drill.getDiameterPX()+"");
	}


}
