package com.lauerbach.casegenerator.svg;
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

public class Point {

	double x, y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(double x, double y, UnitType unit) {
		super();
		this.x = UnitCalculator.getPX(x, unit);
		this.y = UnitCalculator.getPX(y, unit);
	}

	public Point(double x, double y, UnitType unit, Drill drill, int deltaX, int deltaY) {
		super();
		this.x = UnitCalculator.getPX(x, unit) + drill.getRadius() * deltaX;
		this.y = UnitCalculator.getPX(y, unit) + drill.getRadius() * deltaY;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	public Point add(double dx, double dy) {
		return new Point(x + dx, y + dy);
	}

	public Point add(double dx, double dy, UnitType unit) {
		return new Point(x + UnitCalculator.getPX(dx, unit), y + UnitCalculator.getPX(dy, unit));
	}

	public Point rotate(int angleClockwise) {
		switch (angleClockwise) {
		case 0:
			return new Point(x, y);
		case 180:
			return new Point(x, -y);
		case 270:
			return new Point(y, x);
		case 90:
			return new Point(-y, x);
		}
		throw new RuntimeException("Angle " + angleClockwise + " not supported.");
	}

	public Point add(Point offset) {
		return offset.add(x, y);
	}

}
