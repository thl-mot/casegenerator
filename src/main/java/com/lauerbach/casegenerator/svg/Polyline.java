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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.edgegenerator.EdgeTemplateElement.LineType;
import com.lauerbach.casegenerator.edgegenerator.TemplatePoint;

@XmlRootElement( name="polyline")
public class Polyline implements ContainerElement {

	List<Point> points= new ArrayList<>();
	private String stroke;
	private String strokeWidth;
	private String fill="none";
	
	public Polyline() {
	}
	
	public Polyline(String stroke, String strokeWidth) {
		this.stroke= stroke;
		this.strokeWidth= strokeWidth;
	}

	@XmlAttribute
	public String getPoints() {
		String str=null;
		Iterator<Point> i = points.iterator();
		while (i.hasNext()) {
			if (str==null) {
				str= i.next().toString();
			} else {
				str= str+ " "+ i.next().toString();
			}
		}
		return str;
	}
	
	public void setPoints( String str) {
		
	}
	
	public void addPoint( Point p) {
		points.add( p);
	}
	
	public void addAll( List<Point> pointList) {
		points.addAll( pointList);
	}
	
	public void addAll( List<TemplatePoint> pointList, LineType lineType, Point offset, Drill drill) {
		points.addAll( TemplatePoint.getPointList(pointList, lineType, offset, drill));
	}

	@XmlAttribute
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

	public void reverse() {
		Collections.reverse( points);
	}

	public Point last() {
		return points.get( points.size()-1);
	}
	public Point first() {
		return points.get( 0);
	}

	public void removeFirst() {
		points.remove(0);
	}

	public void addAll( Polyline line) {
		points.addAll( line.points);
	}

}
