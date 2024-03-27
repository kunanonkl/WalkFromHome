<?php
    require_once '../includes/DbOperations.php';
    $response = array();
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        if(isset($_POST['username']) and isset($_POST['password'])){
            $db = new DbOperations();
            if($db->userLogin($_POST['username'],$_POST['password'])){
                $user = $db -> getUserByUsername($_POST['username']);
                $response['error'] = false;
                $response['walk_id'] = $user['walk_id'];
                $response['fullname'] = $user['fullname'];
                $response['lastname'] = $user['lastname'];
                $response['phone'] = $user['phone'];
                $response['username'] = $user['username'];
                $response['email'] = $user['email'];
            }else{
                $response['error'] = true;
                $response['message'] = "Invalid Username or Password";
            }
        }else{
            $response['error'] = true;
            $response['message'] = "Missing Fields";
        }
    }else{
        $response['error'] = true;
        $response['message'] = "Something Wrong Please Try Again.";
    }
    echo json_encode($response);
?>