<?php 

class Appointment
{

	public static function add($request_data){
		require("Connection.php");

		$app_title = $request_data["app_title"];
		$app_location = $request_data["app_location"];
		$app_details = $request_data["app_details"];
		$app_day = $request_data["app_day"];
		$app_month = $request_data["app_month"];
		$app_year = $request_data["app_year"];
		$app_start = $request_data["app_start"];
		$app_end = $request_data["app_end"];
		$app_occurrence = $request_data["app_occurrence"];
		$app_user = $request_data["app_user"];

		$mysql_qryu = "SELECT * FROM user_data WHERE user_name = '" .$app_user ."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$id = $row['user_id'];
			}
		}

		$mysql_qry = "insert into appointment_data (app_title, app_location, app_details, app_day, app_month, app_year, app_start, 
												app_end, app_occurrence, app_user) values ('$app_title','$app_location','$app_details',
												'$app_day','$app_month','$app_year','$app_start','$app_end','$app_occurrence', '$id')";

		if($connection->query($mysql_qry) === TRUE) {
			$response = "appointment success";
		}
		else {
			$response = "appointment failed";
		}
		return $response;

		$connection->close();
    }
    
    public static function update($request_data){
		require("Connection.php");

        $prev_title = $request_data["prev_title"];
		$prev_location = $request_data["prev_location"];
		$prev_details = $request_data["prev_details"];
		$prev_day = $request_data["prev_day"];
		$prev_month = $request_data["prev_month"];
		$prev_year = $request_data["prev_year"];
		$prev_start = $request_data["prev_start"];
		$prev_end = $request_data["prev_end"];
		$prev_occurrence = $request_data["prev_occurrence"];

        $app_title = $request_data["app_title"];
		$app_location = $request_data["app_location"];
		$app_details = $request_data["app_details"];
		$app_day = $request_data["app_day"];
		$app_month = $request_data["app_month"];
		$app_year = $request_data["app_year"];
		$app_start = $request_data["app_start"];
		$app_end = $request_data["app_end"];
		$app_occurrence = $request_data["app_occurrence"];
		$app_user = $request_data["app_user"];

		$mysql_qryu = "SELECT * FROM user_data WHERE user_name = '" .$app_user ."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$id = $row['user_id'];
			}
		}

		$mysql_qryu = "SELECT * FROM appointment_data WHERE app_user = '" .$id. "' AND app_title = '".$prev_title."' AND app_location = '".$prev_location."'
		AND app_details = '".$prev_details."' AND app_day = '".$prev_day."' AND app_month = '".$prev_month."' AND app_year = '".$prev_year."'
		AND app_start = '".$prev_start."' AND app_end = '".$prev_end."' AND app_occurrence = '".$prev_occurrence."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$app_id = $row['app_id'];
			}
		}


		$mysql_qry = "UPDATE appointment_data SET app_title = '$app_title', app_location = '$app_location', app_details = '$app_details',
													app_day = '$app_day', app_month = '$app_month', app_year = '$app_year', 
													app_start = '$app_start', app_end = '$app_end', app_occurrence = '$app_occurrence' 
													WHERE app_id = '$app_id'";

		if($connection->query($mysql_qry) === TRUE) {
			$response = "update success";
		}
		else {
			$response = "update failed";
		}
		return $response;

		$connection->close();
    }
    
    public static function delete($request_data){
		require("Connection.php");

		$app_title = $request_data["app_title"];
		$app_location = $request_data["app_location"];
		$app_details = $request_data["app_details"];
		$app_day = $request_data["app_day"];
		$app_month = $request_data["app_month"];
		$app_year = $request_data["app_year"];
		$app_start = $request_data["app_start"];
		$app_end = $request_data["app_end"];
		$app_occurrence = $request_data["app_occurrence"];
		$app_user = $request_data["app_user"];
		
		$mysql_qryu = "SELECT * FROM user_data WHERE user_name = '" .$app_user ."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$id = $row['user_id'];
			}
		}

		$mysql_qryu = "SELECT * FROM appointment_data WHERE app_user = '" .$id. "' AND app_title = '".$app_title."' AND app_location = '".$app_location."'
		AND app_details = '".$app_details."' AND app_day = '".$app_day."' AND app_month = '".$app_month."' AND app_year = '".$app_year."'
		AND app_start = '".$app_start."' AND app_end = '".$app_end."' AND app_occurrence = '".$app_occurrence."'";
		
		$result = mysqli_query($connection, $mysql_qryu);

		if ($result->num_rows > 0) {
			while ($row = mysqli_fetch_array($result))
			{
				$app_id = $row['app_id'];
			}
		}

		$mysql_qry = "delete from appointment_data WHERE app_id = '$app_id'";

		if($connection->query($mysql_qry) === TRUE) {
			$response = "appointment deleted";
		}
		else {
			$response = "appointment delete fail";
		}
		return $response;

		$connection->close();
	}
}
?>
