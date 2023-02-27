# huaxiaoyu


choose:1 or 2
1:docker pull ryan567/java_projects:v1

2:
# database
wget -i -c http://dev.mysql.com/get/mysgl57-community-release-el7-10noarchrpm
sudo yum -y install mysql57-community-release-el7-10.noarch.rpm
sudo yum -y install mysql-community-client
mysql -u root -pxxxxx -e "create database huaxiaoyu default character set utf8mb4 collate utf8mb4 unicode ci
apt-get install redis
service redis start

# back
1.mvn package huaxiaoyu   nohup java -jar huaxiaoyu.jar &
2.mvn package crushingsystem  nohup java -jar crushingsystem &


