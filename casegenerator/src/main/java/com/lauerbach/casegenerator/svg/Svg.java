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


import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
// import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "svg")
// @XmlType( propOrder= {"","",""})
public class Svg extends Container {
	String width;
	String height;

	public Svg() {
	}

	public Svg(String width, String height) {
		super();
		this.width = width;
		this.height = height;
	}

	@XmlAttribute
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@XmlAttribute
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void write(File file,boolean open) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Svg.class);
			Marshaller m = context.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(this, System.out);
			m.marshal(this, file);

			if (open) {
				Runtime.getRuntime().exec("inkscape "+file.getAbsolutePath());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
