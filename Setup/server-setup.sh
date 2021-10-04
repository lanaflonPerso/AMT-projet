# Update repos
apt-get update

# Install openjdk 8 and curl
apt-get -y install openjdk-8-jre-headless curl

# Create group and user tomcat
groupadd tomcat
useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat

# Download tomcat 9 in tmp
cd /tmp
curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.53/bin/apache-tomcat-9.0.53.tar.gz

# Extract Tomcat
mkdir /opt/tomcat
tar xzvf apache-tomcat-*tar.gz -C /opt/tomcat --strip-components=1
cd /opt/tomcat

# Setup permissions
chgrp -R tomcat /opt/tomcat
chmod -R g+r conf
chmod g+x conf
chown -R tomcat webapps/ work/ temp/ logs/

# Create service
echo "[Unit]
Description=Apache Tomcat Web Application Container
After=network.target

[Service]
Type=forking

Environment=JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
Environment=CATALINA_PID=/opt/tomcat/temp/tomcat.pid
Environment=CATALINA_HOME=/opt/tomcat
Environment=CATALINA_BASE=/opt/tomcat
Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

User=tomcat
Group=tomcat
UMask=0007
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target" > /etc/systemd/system/tomcat.service
systemctl daemon-reload
systemctl start tomcat
sudo systemctl enable tomcat

# Create admin
read -p "Enter manager username: " username
read -p "Enter manager password: " password
echo "
<tomcat-users xmlns=\"http://tomcat.apache.org/xml\"
              xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"
              xsi:schemaLocation=\"http://tomcat.apache.org/xml tomcat-users.xsd\"
              version=\"1.0\">

<user username=\"$username\" password=\"$password\" roles=\"manager-gui,admin-gui\"/>
</tomcat-users>
" > /opt/tomcat/conf/tomcat-users.xml

# Set allowed management IP
read -p "Enter Allowed IP: " ip
sed -i "s/allow=\"[^\"]*/allow=\"$ip/gm" /opt/tomcat/webapps/manager/META-INF/context.xml
sed -i "s/allow=\"[^\"]*/allow=\"$ip/gm" /opt/tomcat/webapps/host-manager/META-INF/context.xml
systemctl restart tomcat

# Download and start setup of MariaDB
apt-get -y install mariadb-server
mysql_secure_installation