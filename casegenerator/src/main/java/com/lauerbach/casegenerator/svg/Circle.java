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


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name="circle")
public class Circle implements ContainerElement {

	String x,y,r;
	private String stroke;
	private String strokeWidth;
	private String fill="none";
	
	
	public Circle() {
	}
	
	public Circle(Point point, double r, UnitType unit, String stroke, String strokeWidth) {
		this.x= point.getX()+"";
		this.y= point.getY()+"";
		this.r= UnitCalculator.getPX(r, unit)+"";
		this.stroke= stroke;
		this.strokeWidth= strokeWidth;
	}

	@XmlAttribute( name="cx")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@XmlAttribute( name="cy")
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@XmlAttribute( name="r")
	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	@XmlAttribute(name="stroke")
	public String getStroke() {
		return stroke;
	}

	public void setStroke(String stroke) {
		this.stroke = stroke;
	}

	@XmlAttribute( name="stroke-width")
	public String getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(String strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	@XmlAttribute( name="fill")
	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	
	
}
