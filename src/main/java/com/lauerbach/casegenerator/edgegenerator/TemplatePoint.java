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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeSide;
import com.lauerbach.casegenerator.svg.Point;

public class TemplatePoint implements EdgeTemplateElement {
	public enum Position {
		N, E, W, S, NE, SE, NW, SW
	}

	double outlineX;
	double outlineY;
	Position pos;

	double cutX, cutY;

	public TemplatePoint(double x, double y, Position pos) {
		super();
		this.outlineX = x;
		this.outlineY = y;
		this.pos = pos;
	}

	public void calculateCutPoint(Drill drill) {
		if (pos == Position.E || pos == Position.NE || pos == Position.SE) {
			cutX = outlineX + drill.getRadius();
		}
		if (pos == Position.W || pos == Position.NW || pos == Position.SW) {
			cutX = outlineX - drill.getRadius();
		}
		if (pos == Position.N || pos == Position.NW || pos == Position.NE) {
			cutY = outlineY - drill.getRadius();
		}
		if (pos == Position.S || pos == Position.SW || pos == Position.SE) {
			cutY = outlineY + drill.getRadius();
		}
	}

	public Position getPos() {
		return pos;
	}

	public double getX(LineType type) {
		switch (type) {
		case CUT:
			return cutX;
		case OUTLINE:
			return outlineX;
		}
		return 0;
	}

	public double getY(LineType type) {
		switch (type) {
		case CUT:
			return cutY;
		case OUTLINE:
			return outlineY;
		}
		return 0;
	}
	
	public Point getPoint( LineType lineType, Point offset, Drill drill) {
		calculateCutPoint(drill);
		Point p= new Point( getX(lineType), getY(lineType));
		return offset!=null ? p.add(offset) : p;
	}

	public static List<Point> getPointList(List<TemplatePoint> input, LineType lineType, Point offset, Drill drill) {
		List<Point> out = new ArrayList<>();
		Iterator<TemplatePoint> i = input.iterator();
		while (i.hasNext()) {
			TemplatePoint templatePoint = (TemplatePoint) i.next();
			out.add( templatePoint.getPoint(lineType, offset, drill));
		}
		return out;
	}
	
	public Point getTransformedPoint( LineType lineType, Point offset, EdgeSide panelSide) {
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
		Point p= new Point( getX(lineType), getY(lineType)).rotate(angleClockwise).add(offset);
		return p;
	}

}
