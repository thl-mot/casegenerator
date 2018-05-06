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
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

public class Container {

	private String transform;

	private List<ContainerElement> elements = new ArrayList<>();

	@XmlElementRefs({ @XmlElementRef(name = "line", type = Line.class),
			@XmlElementRef(name = "polyline", type = Polyline.class), @XmlElementRef(name = "g", type = Group.class),
			@XmlElementRef(name = "rect", type = Rectangle.class),
			@XmlElementRef(name = "circle", type = Circle.class) })
	public List<ContainerElement> getElements() {
		return this.elements;
	}

	public void setElements(List<ContainerElement> elements) {
		this.elements = elements;
	}

	public void add(ContainerElement element) {
		getElements().add(element);
	}

	public void addAll(List<ContainerElement> elements) {
		getElements().addAll(elements);
	}

	@XmlAttribute(name = "transform")
	public String getTransform() {
		return transform;
	}

	public void setTransform(String transform) {
		this.transform = transform;
	}

	public void addTranslate(Point p) {
		if (p==null) {
			return;
		}
		if (transform == null) {
			transform = "";
		}
		transform = (transform + " translate(" + p.getX() + "," + p.getY() + ")").trim();
	}

	public void addRotate(double degree, Point p) {
		if (transform == null) {
			transform = "";
		}
		transform = (transform + " rotate(" + degree + "," + p.getX() + "," + p.getY() + ")").trim();

	}

}
