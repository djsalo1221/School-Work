@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "E:\stopwatch\labels.tmp" -fI -W+ie -C V2E -o "E:\stopwatch\stopwatch.hex" -d "E:\stopwatch\stopwatch.obj" -e "E:\stopwatch\stopwatch.eep" -m "E:\stopwatch\stopwatch.map" "E:\stopwatch\stopwatch.asm"
