#!/bin/bash


cd src 


javac --module-path $FX --add-modules javafx.controls,javafx.fxml Launcher.java

jar cfm ../out/roundrobin-simulation.jar ../MANIFEST.MF  *.class  gui.fxml

rm *.class
cd ..


java --module-path $FX --add-modules javafx.controls,javafx.fxml -jar ./out/roundrobin-simulation.jar 2&> /dev/null
