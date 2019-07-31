<?php

class Response
{
    const HTTP_VERSION = "HTTP/1.1";

    public static function sendResponse($data)
    {
        if ($data) {
            $code = 200;
            $message = 'OK';
        } else {
            $code = 404;
            $data = array('error' => 'Not Found');
            $message = 'Not Found';
        }

        header(self::HTTP_VERSION . " " . $code . " " . $message);
        $content_type = isset($_SERVER['CONTENT_TYPE']) ? $_SERVER['CONTENT_TYPE'] : $_SERVER['HTTP_ACCEPT'];
        header('Access-Control-Allow-Origin: *');
        echo $data;
    }
}