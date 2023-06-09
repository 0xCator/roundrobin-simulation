#!/bin/bash

JAR_PATH="./out/roundrobin-simulation.jar"
ARG=${1:-not}
MANIFEST="./MANIFEST.MF"
JAVAFX="/usr/lib/jvm/javafx-sdk-20.0.1/" 

if [[ ! -e "${JAVAFX}" ]]; then 

    echo ""
    echo -e "\e[31m[-!]javafx not installed\e[0m"
    echo ""
    wget https://download2.gluonhq.com/openjfx/20.0.1/openjfx-20.0.1_linux-x64_bin-sdk.zip  
    unzip ./openjfx-20.0.1_linux-x64_bin-sdk.zip
    cp -r ./javafx-sdk-20.0.1/ /usr/lib/jvm/ 
    export FX="/usr/lib/jvm/javafx-sdk-20.0.1/lib/"
fi


if [[ ! -e "${MANIFEST}" ]]; then
    echo "Manifest-Version: 1.0" > MANIFEST.MF
    echo "Main-Class: Launcher" >> MANIFEST.MF
fi

if [ "${ARG}" = "d" ]; then 
    rm ./out/roundrobin-simulation.jar
    echo ""
    echo -e "\033[32m[*!] JAR is  deleted  "
    exit 0
fi

if [[ ! -e "${JAR_PATH}" ]]; then 

    cd src 

    javac --module-path $FX --add-modules javafx.controls,javafx.fxml Launcher.java

    jar cvfm ../out/roundrobin-simulation.jar ../MANIFEST.MF  *.class  gui.fxml

    rm *.class
    cd ..

    echo ""
    echo -e "\033[32m[*!] jar was built in /out/."
    echo -e "\033[32m[+!] 'r' option to execute jar file."
fi

if [ "${ARG}" = "r" ]; then 
    java --module-path $FX --add-modules javafx.controls,javafx.fxml -jar ./out/roundrobin-simulation.jar 2&> /dev/null &
    exit 0
else 
    echo ""
    echo -e "\033[32m[*!] Jar was already built in /out/."
    echo -e "\033[32m[+!] 'r' option to execute jar file."
fi
