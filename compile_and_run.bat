@echo off
echo ========================================
echo MedConnect
echo ========================================
echo.

echo Setting up classpath...
set "CLASSPATH=.;mysql-connector-java-5.1.49.jar;jcalendar-1.4.jar;gradient-icon-font.jar;TimingFramework-0.55.jar"

echo.
echo Compiling Java files...
javac -d . -cp "%CLASSPATH%" *.java
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Compilation failed!
    echo Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo.
echo Starting the application...
java -cp "%CLASSPATH%" online.medicine.donation.system.HomePage
pause