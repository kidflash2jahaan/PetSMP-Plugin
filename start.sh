pkill -f "java --enable-preview -Xms4G -Xmx4G -jar server.jar --nogui"
cp .env server/
mvn clean package
rm target/original*.jar
mv target/*.jar server/plugins/
cd server || exit
exec java --enable-preview -Xms4G -Xmx4G -jar server.jar --nogui