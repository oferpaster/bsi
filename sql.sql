battery:
SELECT COUNT(battery.idbattery) AS 'numberOfId' FROM `bsi_db`.`battery`;
SELECT battery.idbattery, battery.volt, battery.amper FROM bsi_db.battery WHERE idclient = ? ;
INSERT INTO `bsi_db`.`battery` VALUES (?,?,?,?);

client:
SELECT client.idclient FROM bsi_db.client WHERE client.name = ?;
SELECT * FROM bsi_db.client;
INSERT INTO `bsi_db`.`client` VALUES (?,?);

clientbattery:
INSERT INTO `bsi_db`.`clientbattery`(`batteryid`,`clientid`,`status`) VALUES (?,?,?);
UPDATE bsi_db.clientbattery SET refurbished = ? ,scrap = ? WHERE batteryid = ? AND clientid = ? ;


open connection:

MySqlConnection con = new MySqlConnection();
con.update(con.getConn(), sqlmsg);

gitting result:

public ArrayList<Object> getSqlRslt(MySqlConnection con) {
		ArrayList<Object> result = null;
		while ((result = con.getResult()) == null) {
		}
		return result;
	}
