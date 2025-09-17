#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin $(find ../src/main/java -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin jeff.ui.Jeff < input.txt > ACTUAL.TXT

# normalize line endings to UNIX without requiring dos2unix
awk '{ sub(/\r$/,"",$0); print }' ACTUAL.TXT > ACTUAL-UNIX.TXT
awk '{ sub(/\r$/,"",$0); print }' EXPECTED.TXT > EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL-UNIX.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
