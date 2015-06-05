<?php
//World4you Service.php
$hostname = "mysqlsvr44.world4you.com";
$username ="sql2388559";
$password ="Bullshitbingo21";
$db = "2388559db5";

$conn  = new mysqli($hostname,$username,$password,$db);
 
if($conn->connect_error){
 
$response ="could not connect to the database";
 
die(print(json_encode($response)));}

mysqli_set_charset( $conn, 'utf8');

//print("connection successful - geil");
if($_GET["get"]){
    GetAllRows($_GET["get"],$conn);
}elseif($_GET["InsertUser"]){
    InsertUser($_GET["InsertUser"],$conn);

}elseif($_GET["InsertProduct"]){
    InsertProduct($_GET["InsertProduct"],$conn);

}elseif($_GET["InsertProductHasAllergen"]){
    InsertProductHasAllergen($_GET["InsertProductHasAllergen"],$conn);

}elseif($_GET["InsertUserHasAllergen"]){
    InsertUserHasAllergen($_GET["InsertUserHasAllergen"],$conn);
}elseif($_GET["InsertProductHasCategory"]){
    InsertProductHasCategory($_GET["InsertProductHasCategory"],$conn);
}elseif($_GET["DeleteUserHasAllergen"]){
    DeleteUserHasAllergen($_GET["UserID"],$_GET["AllergenID"],$conn);
}elseif($_GET["UpdateUser"]){
    UpdateUser($_GET["UpdateUser"],$conn);
}elseif($_GET["ProductHasAllergen"]){
    ProductHasAllergen($_GET["ProductHasAllergen"],$conn);
}else{
     print("Invalid Arguments!");
}



$conn->close();

function GetAllRows($tablename,$conn){
    if($tablename == "Product"){

        $q = "SELECT `ProductID`, `Productname`, `Description`,  `Image`, `EANCode` FROM `Product`";
    }elseif($tablename == "Allergen") {
        $q = "SELECT * FROM `Allergen` ORDER BY `AllergenID`";
    }elseif($tablename == "ProductHasAllergen") {
        $q = "SELECT * FROM Allergen a INNER JOIN ProductHasAllergen pha ON (AllergenID = Allergen_AllergenID)";
	}else{
        $q ="SELECT * FROM ".$tablename;
    }
    //echo $q;

    $result = $conn->query($q);
    //var_dump($result);
    $neuesarray = array();

    while($row = $result->fetch_assoc())
    {
        if($tablename == "Product"){
            $row["Image"] = base64_encode($row["Image"]);
        }
        array_push($neuesarray, $row);
        // print("Test");
    }
//var_dump($neuesarray);

    $output = json_encode($neuesarray);
//$output = substr($output,1,-1);
    if($output == null){
        print("Output is null");}else{

        print($output);
    }
}

function InsertUser($json,$conn){
    $user = json_decode($json);

    $q ="INSERT INTO `User`(`UserID`, `Username`, `Password`, `Mailaddress`, `Firstname`, `Lastname`, `DoB`, `[Active]`) VALUES
     (null,'".$user ->{"Username"}.
        "','".$user ->{"Password"}.
        "','".$user ->{"Mailaddress"}.
        "','".$user ->{"Firstname"}.
        "','".$user ->{"Lastname"}.
        "',STR_TO_DATE('".$user ->{"DoB"}.
        "','%Y-%m-%d'),1)";

		/*$q ="INSERT INTO `User`(`UserID`, `Username`, `Password`, `Mailaddress`, `Firstname`, `Lastname`, `DoB`, `[Active]`) VALUES
        (null, '".$user ->{"DoB"}.
        "','".$user ->{"Password"}.
        "','".$user ->{"Mailaddress"}.
        "','".$user ->{"Firstname"}.
        "','".$user ->{"Lastname"}.
        "', CURRENT_DATE,1)";*/
	
    mysqli_query($conn,$q);
	echo $q;
}

function UpdateUser($json,$conn){
    $user = json_decode($json);

    $q ="Update `User` SET  `Username` = '".$user ->{"Username"}."', `Password` = '".$user ->{"Password"}."', `Mailaddress` = '".$user ->{"Mailaddress"}."', `Firstname` = '".$user ->{"Firstname"}."', `Lastname` = '".$user ->{"Lastname"}."', `DoB` = STR_TO_DATE('".$user ->{"DoB"}."','%Y-%m-%d'), `[Active]` = ".$user->{"Active"}."
     WHERE UserID = ".$user ->{"UserID"} ;

    mysqli_query($conn,$q);
    echo $q;
}

function InsertProduct($json,$conn){
    $product = json_decode($json);


    $q = "INSERT INTO `Product`(`ProductID`, `Productname`, `Description`, `Image`, `EANCode`) VALUES (null,'".
        $product -> {"Productname"}.
        "','".
        $product -> {"Description"}.
        "',null,'".
        $product -> {"EANCode"}.
        "')";

    mysqli_query($conn,$q);
}
function InsertProductHasAllergen($json,$conn){
    $jsonObj = json_decode($json);
    $q = "INSERT INTO `ProductHasAllergen`(`Allergen_AllergenID`, `Product_ProductID`) VALUES (".$jsonObj -> {"AllergenID"}.",".$jsonObj -> {"ProductID"}.")";
    mysqli_query($conn,$q);
}
function InsertUserHasAllergen($json,$conn){
    $jsonObj = json_decode($json);
    $q = "INSERT INTO `UserHasAllergen`(`User_UserID`, `Allergen_AllergenID`) VALUES (".$jsonObj->{"UserID"}.",".$jsonObj->{"AllergenID"}.")";
    mysqli_query($conn,$q);
}
function InsertProductHasCategory($json,$conn){
    $jsonObj = json_decode($json);
    $q = "INSERT INTO `ProductCategoryHasProduct`(`ProductCategory_ProductCatID`, `Product_ProductID`) VALUES (".$jsonObj->{"ProductCatID"}.",".$jsonObj->{"ProductID"}.")";
    mysqli_query($conn,$q);
}

function DeleteUserHasAllergen($userID,$allergenID,$conn){
    $q = "DELETE FROM `UserHasAllergen` WHERE User_UserID = ".$userID." && Allergen_AllergenID = ".$allergenID;
    echo $q;
    mysqli_query($conn,$q);
}

function ProductHasAllergen($json,$conn){
    $jsonObj = json_decode($json);
    $q = "INSERT INTO `ProductHasAllergen`(`Allergen_AllergenID`, `Product_ProductID`) VALUES (".$jsonObj->{"AllergenID"}.",".$jsonObj->{"ProductID"}.")";
    mysqli_query($conn,$q);
}
?>