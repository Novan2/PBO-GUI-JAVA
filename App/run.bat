@echo off
echo Compiling Main.java...
javac --module-path "C:\javafx-sdk-25.0.1\lib" --add-modules javafx.controls -d bin src\Main.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Running JavaFX Application...
java --module-path "C:\javafx-sdk-25.0.1\lib" --add-modules javafx.controls,javafx.graphics,javafx.base --enable-native-access=ALL-UNNAMED --add-opens javafx.graphics/com.sun.glass.utils=ALL-UNNAMED --add-opens javafx.graphics/com.sun.glass.ui=ALL-UNNAMED -cp bin Main

echo.
echo Application closed.
pause
