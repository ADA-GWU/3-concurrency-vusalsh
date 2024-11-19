#!/bin/bash
mkdir -p bin

javac -d bin src/ImagePanel.java src/ImageProcessor.java src/Main.java

java -cp bin Main "$@"
