#!/bin/bash

JAR_PATH="./out/roundrobin-simulation.jar"
ARG=${1:-not}


if [[ ! -e "${JAR_PATH}" ]]; then 

    cd src 

    javac --module-path $FX --add-modules javafx.controls,javafx.fxml Launcher.java

    jar cvfm ../out/roundrobin-simulation.jar ../MANIFEST.MF  *.class  gui.fxml

    rm *.class
    cd ..

fi

if [ "${ARG}" = "r" ]; then 
    java --module-path $FX --add-modules javafx.controls,javafx.fxml -jar ./out/roundrobin-simulation.jar 2&> /dev/null &
    exit 0
else 
    echo ""
    echo -e "\033[32m[*!] Jar was build in /out/."
    echo -e "\033[32m[+!] 'r' option to execute jar file."
fi
