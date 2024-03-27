<?php
       
        class DbOperations{
            private $con;

            function __construct(){
                
                require_once dirname(__FILE__).'/DbConnect.php';
                $db = new DbConnect();
                $this->con = $db->connect();
            }

            /* CRUD->C->CREATE */

           public function createUser($fullname,$lastname,$phone,$username, $password,$email){
                if($this -> isuserExist($username,$email)){
                    return 0;
                }
                else{$password = md5($password);
                    $stmt = $this->con->prepare("INSERT INTO `users` (`walk_id`, `fullname`, `lastname`, `phone`, `username`, `password`, `email`)
                                                    VALUES (NULL,?,?,?,?,?,?);");
                    $stmt->bind_param("ssssss",$fullname,$lastname,$phone,$username,$password,$email);
                    if($stmt->execute()){
                        return 1;
                    }
                    else{
                        return 2;
                    }
                }
                
            }
            public function userLogin($username,$pass_word){
                $password = md5($pass_word);
                $stmt = $this->con->prepare("SELECT walk_id FROM users WHERE username=? AND password =?");
                $stmt->bind_param("ss",$username,$password);
                $stmt->execute();
                $stmt->store_result();
                return $stmt->num_rows > 0;
            }
            public function getUserByUsername($username){
                $stmt = $this->con->prepare("SELECT * FROM users WHERE username=?");
                $stmt -> bind_param("s",$username);
                $stmt -> execute();
                return $stmt->get_result()->fetch_assoc();
            }
            private function isuserExist($username,$email){
                $stmt = $this->con->prepare("SELECT walk_id FROM users WHERE username = ? OR email = ?");
                $stmt->bind_param("ss",$username,$email);
                $stmt->execute();
                $stmt->store_result();
                return $stmt -> num_rows() > 0 ;
            }

        }
        ?>