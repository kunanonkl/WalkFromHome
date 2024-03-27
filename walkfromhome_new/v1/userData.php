<?php
   // require_once '../includes/DbOperations.php';

use LDAP\Result;

    $connection = mysqli_connect('localhost','root','','new_walkfromhome');
    $result = array();
    $result['data'] = array();
    $select = "SELECT * from walkdata";
    $response = mysqli_query($connection,$select);
    while($row = mysqli_fetch_array($response)){
        $index['userId'] = $row['1'];
        if($index['userId'] == $_POST['userId']){
            $index['distance'] = $row['2'];
            array_push($result['data'],$index);
        }  
    }
    $result["success"] = "1";
    echo json_encode($result);
?>