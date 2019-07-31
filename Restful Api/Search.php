<?php 

class Search
{
	public static function weekAppointments($request_data){

		require('Connection.php');

		$app_day = $request_data["app_day"];
		$app_month = $request_data["app_month"];
		$app_year = $request_data["app_year"];
		$app_user = $request_data["app_user"];

		$mysql_qryu = "SELECT * FROM user_data WHERE user_name = '" .$app_user ."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$id = $row['user_id'];
			}
		}

		$mysql_qry = "SELECT * FROM appointment_data WHERE app_day = '" . $app_day . "' AND 
						app_month = '" . $app_month . "' AND app_year = '" . $app_year . "' AND
						app_user = '" . $id . "'";

		$result = mysqli_query($connection, $mysql_qry);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$response .= $row['app_day'] . " ==== " . $row['app_month'] . " ==== " . $row['app_year'] . 
				" ==== " . $row['app_title'] . " ==== " . $row['app_location'] . " ==== " .
				$row['app_start'] . " ==== " . $row['app_end'] . " ==== " . $row['app_occurrence'] . " ==== " .
				$row['app_details'] . '<searchAppointment>';
			}
		}
		else{
			$response = "no appointments";
		}
		
		return $response;

		$connection->close();
		
	}
	
	public static function specificDay($request_data){

		require('Connection.php');

		$app_title = $request_data["app_title"];
		$app_location = $request_data["app_location"];
		$app_day = $request_data["app_day"];
		$app_month = $request_data["app_month"];
		$app_year = $request_data["app_year"];
		$app_start = $request_data["app_start"];
		$app_end = $request_data["app_end"];
		$app_user = $request_data["app_user"];

		$mysql_qryu = "SELECT * FROM user_data WHERE user_name = '" .$app_user ."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$id = $row['user_id'];
			}
		}

		$mysql_qry = "SELECT * FROM appointment_data";

		if($app_title == ''){
			$mysql_qry .= " WHERE !(app_title = '') AND ";
		}

		else{
			$mysql_qry .= " WHERE app_title = '" . $app_title . "' AND ";
		}


		if($app_location == ''){
			$mysql_qry .= "!(app_location = '') AND ";
		}

		else{
			$mysql_qry .= "app_location = '" . $app_location . "' AND ";
		}

		if($app_day == ''){
			$mysql_qry .= "!(app_day = '') AND ";
		}

		else{
			$mysql_qry .= "app_day = '" . $app_day . "' AND ";
		}


		if($app_month == ''){
			$mysql_qry .= "!(app_month = '') AND ";
		}

		else{
			$mysql_qry .= "app_month = '" . $app_month . "' AND ";
		}

		if($app_year == ''){
			$mysql_qry .= "!(app_year = '') AND ";
		}

		else{
			$mysql_qry .= "app_year = '" . $app_year . "' AND ";
		}

		if($app_start == ''){
			$mysql_qry .= "!(app_start = '') AND ";
		}

		else{
			$mysql_qry .= "app_start = '" . $app_start . "' AND ";
		}

		if($app_end == ''){
			$mysql_qry .= "!(app_end = '') AND ";
		}

		else{
			$mysql_qry .= "app_end = '" . $app_end . "' AND ";
		}

		$mysql_qry .= "app_user = '" . $id . "'";

		$result = mysqli_query($connection, $mysql_qry);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$response .= $row['app_day'] . " ==== " . $row['app_month'] . " ==== " . $row['app_year'] . 
				" ==== " . $row['app_title'] . " ==== " . $row['app_location'] . " ==== " .
				$row['app_start'] . " ==== " . $row['app_end'] . " ==== " . $row['app_occurrence'] . " ==== " .
				$row['app_details'] . '<searchAppointment>';
			}
		}
		else{
			$response = "no appointments";
		}
		
		return $response;

		$connection->close();
	}
}
?>