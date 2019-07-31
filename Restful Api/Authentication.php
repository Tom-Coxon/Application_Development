<?php 

class Authentication
{
	public static function login($request_data){

		require('Connection.php');

		$user_name = $request_data["user_name"];
		$user_password = $request_data["user_password"];
		$mysql_qry = "select * from user_data where user_name like '$user_name' and user_password like '$user_password';";
		$result = mysqli_query($connection,$mysql_qry);
		if(mysqli_num_rows($result) > 0) {
			$response = "login success";
		}
		else {
			$response = "login failed";
		}
		
		return $response;

		$connection->close();
		
	}
	
	public static function register($request_data){

		require('Connection.php');

		$user_name = $request_data["user_name"];
		$user_password = $request_data["user_password"];
		$mysql_qry = "insert into user_data (user_name, user_password) values ('$user_name','$user_password')";

		if($connection->query($mysql_qry) === TRUE) {
			$response = "register success";
		}
		else {
			$response = "register failed";
		}

		return $response; 

		$connection->close();
	}
}
?>
