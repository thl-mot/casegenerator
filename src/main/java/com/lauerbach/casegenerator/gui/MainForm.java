package com.lauerbach.casegenerator.gui;
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

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class MainForm implements Initializable {

	Camera camera = new PerspectiveCamera(true);
	
	@FXML
	Pane previewPanel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		camera.getTransforms().addAll (
                new Rotate(-30, Rotate.Y_AXIS),
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -280));
		camera.setNearClip(0.1);
		camera.setFarClip(10000);
		
		Group root= new Group();
		SubScene subScene= new SubScene( root, 600, 500);
		subScene.setCamera( camera);
		previewPanel.getChildren().add( subScene);
		
		final PhongMaterial redMaterial = new PhongMaterial();
	       redMaterial.setSpecularColor(Color.ORANGE);
	       redMaterial.setDiffuseColor(Color.RED);
		
		
		Box box= new Box(50, 1, 1); 
		box.setMaterial( redMaterial);
		root.getChildren().add( box);
		
		box= new Box(1, 50, 1); 
		box.setMaterial( redMaterial);
		root.getChildren().add( box);
		
		box= new Box(1, 1, 50); 
		box.setMaterial( redMaterial);
		root.getChildren().add( box);

	}

	@FXML
	public void turnLeft() {
		camera.setRotate( camera.getRotate()-10);
	}
	
	@FXML
	public void turnRight() {
		camera.setRotate( camera.getRotate()+10);
	}
	
}
