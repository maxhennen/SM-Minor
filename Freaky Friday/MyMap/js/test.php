<?php
    error_reporting(E_ALL);
    $data = $_POST['country']; // the key we sent was "something"
    $f = fopen('visited-countries.txt', 'w+');
    fwrite($f, $data);
    fclose($f);
?>