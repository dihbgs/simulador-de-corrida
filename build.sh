#!/bin/bash
set -e

SRC="src/main/java"
BIN="bin"

echo "Compiling files..."

SOURCES=$(find "$SRC" -name "*.java")
javac -d "$BIN" $SOURCES

echo "Copying resources folder..."
cp -r "$SRC/../resources" "$BIN"

echo "Finished compilation!"

echo "Creating jar file..."
cd "$BIN"
jar cfe simulator.jar simulator.Simulator simulator/*.class resources
cd ..
echo "Finished creating jar!"

if ! [ -z "$1" ]; then
	echo "Running $1..."
	java -cp "$BIN" "$1"
	echo "Finished running file!"
fi
