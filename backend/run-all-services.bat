@echo off
echo Starting all microservices...

:: Set Java options for all services
set JAVA_OPTS=-Xmx256m

:: Define service names and ports
set SERVICE1=auth-service
set PORT1=8084

set SERVICE2=user-service
set PORT2=8085

set SERVICE3=service-provider-service
set PORT3=8081

set SERVICE4=booking-service
set PORT4=8082

set SERVICE5=review-service
set PORT5=8083

set SERVICE6 = payment-service
set PORT6 = 8084

:: Start each service in a new command window
echo Starting %SERVICE1% on port %PORT1%...
start "Service: %SERVICE1%" cmd /c "cd %SERVICE1% && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=%PORT1% %JAVA_OPTS%""

echo Starting %SERVICE2% on port %PORT2%...
start "Service: %SERVICE2%" cmd /c "cd %SERVICE2% && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=%PORT2% %JAVA_OPTS%""

echo Starting %SERVICE3% on port %PORT3%...
start "Service: %SERVICE3%" cmd /c "cd %SERVICE3% && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=%PORT3% %JAVA_OPTS%""

echo Starting %SERVICE4% on port %PORT4%...
start "Service: %SERVICE4%" cmd /c "cd %SERVICE4% && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=%PORT4% %JAVA_OPTS%""

echo Starting %SERVICE5% on port %PORT5%...
start "Service: %SERVICE5%" cmd /c "cd %SERVICE5% && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=%PORT5% %JAVA_OPTS%""

echo Starting %SERVICE6% on port %PORT6%...
start "Service: %SERVICE6%" cmd /c "cd %SERVICE6% && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=%PORT6% %JAVA_OPTS%""



echo All services have been started.
echo.
echo Service status:
echo - %SERVICE1%: http://localhost:%PORT1%
echo - %SERVICE2%: http://localhost:%PORT2%
echo - %SERVICE3%: http://localhost:%PORT3%
echo - %SERVICE4%: http://localhost:%PORT4%
echo - %SERVICE5%: http://localhost:%PORT5%
echo - %SERVICE6%: http://localhost:%PORT6%
echo.
echo Press any key to terminate all services...

pause >nul
taskkill /fi "WindowTitle eq Service: %SERVICE1%*" /T /F
taskkill /fi "WindowTitle eq Service: %SERVICE2%*" /T /F
taskkill /fi "WindowTitle eq Service: %SERVICE3%*" /T /F
taskkill /fi "WindowTitle eq Service: %SERVICE4%*" /T /F
taskkill /fi "WindowTitle eq Service: %SERVICE5%*" /T /F

echo All services have been terminated. 