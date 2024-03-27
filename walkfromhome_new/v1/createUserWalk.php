
<?php   
        require_once '../includes/DbOperations.php';
        $response = array();
        if($_SERVER['REQUEST_METHOD'] == 'POST'){
            if(isset($_POST['distance']) and isset($_POST['user_id']) ){
                $db = new DbOperations();
                $result = $db->createUserWalkData($_POST['distance'],$_POST['user_id']);
                if($result == 1){
                    $response['error'] = false;
                    $response['message'] = "บันทึกข้อมูลสำเร็จแล้ว";
                }
                elseif($result == 2){
                    $response['error'] = true;
                    $response['message'] = "ไม่สามารถบันทึกข้อมูลได้";
                }
            }
            else{
                $response['error'] = true;
                $response['message'] = "เกิดปัญหากับฐานข้อมูลรอการดำเนินการแก้ไข";
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = "ไม่สามารถเชื่อมต่อกับฐานข้อมูลได้ ";
        }
        echo json_encode($response);
        ?>
        