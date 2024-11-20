@echo off
if not exist "bin" (
    mkdir bin
)

javac -d bin src\ImagePanel.java src\ImageProcessor.java src\Main.java

java -cp bin Main %*
