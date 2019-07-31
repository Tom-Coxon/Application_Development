<?php


require('Authentication.php');
require('Search.php');
require('Appointment.php');

class Request
{
    private static $method_type = array('get', 'post', 'put', 'patch', 'delete');

    public static function getRequest()
    {
        $method = strtolower($_SERVER['REQUEST_METHOD']);
        if (in_array($method, self::$method_type)) {
            $data_name = $method . 'Data';
            return self::$data_name($_REQUEST);
        }
        return false;
    }

    private static function getData($request_data)
    {
        return "There are no gets availible";
    }

    private static function postData($request_data)
    {
        if ($request_data['CALL'] == "login"){
			return Authentication::login($request_data);
		}

		else if($request_data['CALL'] == "register"){
			return Authentication::register($request_data);
		}

		else if($request_data['CALL'] == "week_app"){
			return Search::weekAppointments($request_data);
        }

        else if($request_data['CALL'] == "search"){
			return Search::specificDay($request_data);
        }

        else if($request_data['CALL'] == "add"){
			return Appointment::add($request_data);
        }
        
        else if($request_data['CALL'] == "delete"){
			return Appointment::delete($request_data);
        }
        
        else if($request_data['CALL'] == "update"){
			return Appointment::update($request_data);
		}
    }

    private static function putData($request_data)
    {
        return "There are no puts availible";
    }

    private static function patchData($request_data)
    {
        return "There are no patch availible";
    }

    private static function deleteData($request_data)
    {
        return "There are no deletes availible";
    }
}