<?php

require('Request.php');

require('Response.php');

$data = Request::getRequest();

Response::sendResponse($data);

?>

