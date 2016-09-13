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

@XmlRootElement( name="rect")
public class Rectangle implements ContainerElement {

	String x, y, width, height, stroke, strokeWidth;
	String fill="none";
	
	String rx, ry;

	public Rectangle() {
	}
	
	public Rectangle(String x, String y, String width, String height, String stroke, String strokeWidth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.stroke = stroke;
		this.strokeWidth= strokeWidth;
	}

	public Rectangle( Point p1, Point p2, String stroke, String strokeWidth) {
		this.x = Math.min(p1.x, p2.x)+"px";
		this.y = Math.min(p1.y, p2.y)+"px";
		this.width = Math.abs(p2.x-p1.x)+"px";
		this.height = Math.abs(p2.y-p1.y)+"px";
		
		this.stroke = stroke;
		this.strokeWidth= strokeWidth;
	}

	
	@XmlAttribute
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@XmlAttribute
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@XmlAttribute
	public String getWidth() {
		return width;
	}

	public void setWidth(String w) {
		this.width = w;
	}

	@XmlAttribute
	public String getHeight() {
		return height;
	}

	public void setHeight(String h) {
		this.height = h;
	}

	@XmlAttribute
	public String getStroke() {
		return stroke;
	}

	public void setStroke(String stroke) {
		this.stroke = stroke;
	}

	@XmlAttribute(name = "stroke-width")
	public String getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(String strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	@XmlAttribute(name = "fill")
	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	@XmlAttribute( name="rx")
	public String getRx() {
		return rx;
	}

	public void setRx(String rx) {
		this.rx = rx;
	}

	@XmlAttribute( name="ry")
	public String getRy() {
		return ry;
	}

	public void setRy(String ry) {
		this.ry = ry;
	}
	
}
