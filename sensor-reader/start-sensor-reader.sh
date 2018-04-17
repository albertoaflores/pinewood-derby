export SPRING_PROFILES_ACTIVE=mongodb
#export SPRING_PROFILES_ACTIVE=mock,mongodb

JAVA_OPTS="-Dlogging.level.io.cybertech.pd.sensor.usb=debug -Dlogging.level.jssc=debug"

java $JAVA_OPTS -jar ./target/sensor-reader-0.0.1-SNAPSHOT.jar
