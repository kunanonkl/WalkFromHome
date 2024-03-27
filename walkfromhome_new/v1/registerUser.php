
<?php   
        require_once '../includes/DbOperations.php';
        $response = array();
        if($_SERVER['REQUEST_METHOD'] == 'POST'){
            if(isset($_POST['fullname']) and isset($_POST['lastname']) and isset($_POST['phone']) and isset($_POST['username']) and isset($_POST['password']) and isset($_POST['email'])){
                $db = new DbOperations();
                $result = $db->createUser($_POST['fullname'],$_POST['lastname'],$_POST['phone'],$_POST['username'],$_POST['password'],$_POST['email']);
                if($result == 1){
                    $response['error'] = false;
                    $response['message'] = "User Registered Successfully.";
                }
                elseif($result == 2){
                    $response['error'] = true;
                    $response['message'] = "Something Wrong Please Try Again.";
                }
                elseif($result == 0){
                    $response['error'] = true;
                    $response['message'] = "Username or Email ,Already In Our Database";
                }

            }
            else{
                $response['error'] = true;
                $response['message'] = "Missing Fields";
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = "Invalid Username or Password";
        }
        echo json_encode($response);
        ?>
        