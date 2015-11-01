# ncellApp
perfuntory application to read data from my sql database and display on android phone


client will send the server name value pairs whose first pair is for the type of request which may be like login or foodItem or postComment or getLikes or getComments etc by which the server has to determine what kind of json response to form

TASK = LOGIN
(request => "login"
username => #username
password => #password)

JSON RESPONSE
{"success" : #success_status, success is an integer
 "message" : #message,
 "users_id" : #users_id}  user_id is an integer


TASK = REGISTER
(request  => "register"
 username => #username
 password => #password
 email => #email)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
 "users_id" : #users_id}


TASK =   ITEMS   // client is asking for the food items available in the database
( request => "items"
  "item" => #itemType)   //  "food" this will be food for now but can be anything like clothes and so on 

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
 "Items" : [
     {"food_id" : 1, "name" : #foodName , "food_icon" : #food_icon},
     {"food_id" : 2, "name" : #foodName, "food_icon" : #food_icon},
     {"food_id" : 3, "name" : #foodName, "food_icon" : #food_icon},
     ..........
     ..........
    ]
}


TASK = ARTICLE  // client asking for the description of the specific item of the database
(request => "article"
 itemName=> #itemName) // this will contain the name of the food for now
 
JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
 "food_id" : #food_id,
 "name" : #food_name,
 "image" : #food_image,
 "imageCaption" : #imageCaption,
 "article" : #article}


TASK = GET PLACES
(request => "getPlaces"
 item => #itemName)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
 "places" : [
     {"name" : #placeName, "location" : #location}
     ....................
 ]
}


TASK = GET COMMENTS  // client requesting for all the comments made on the specific item
(request => "getComments"
 item => #itemName
) 

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
 "comments" : [
      { "username" : #username,
        "users_id" : #user_id,
        "comment" : #comment1},
      { "username" : #username,
        "users_id" : #user_id,
        "comment" : #comment2},
      { "username" : #username,
        "users_id" : #user_id,
        "comment" : #comment3},
       ............
       ............
  ]
}


TASK = GET LIKES COUNT 
(request => "getLikes"
 item => #itemName
 username => #username)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
 "likesCount" : #likesCount,
 "dislikesCount" : #dislikesCount
 "userLike": #user'sLikeStatus    //whether the user has liked or disliked or none in the specific item
}


TASK = POST COMMENTS // client posting a comment to be saved in the database
(request => "postComments"
 item => #itemName
 username => #username
 comment =>#comment
)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
}


TASL = POST LIKE 
(request => "postLikes"
 item => #itemName
 username => #username)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message,
}


TASK = GET WISHLIST
(request => "getWishList"
 username => #username
)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message
 "items" : [
     {"itemType" : "food",  //for now
       "itemName" : #item_name,
       "itemIcon" :  #itemIcon}
     {"itemType" : "food",  //for now
       "itemName" : #item_name,
       "itemIcon" :  #itemIcon}
     {"itemType" : "food",  //for now
       "itemName" : #item_name,
       "itemIcon" :  #itemIcon}
      ................
  ]
}

TASK = ADD TO WISHLIST
(request => "addToWishList"
 username => #username
 itemName => #itemName
)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message
}

TASK = DELETE FROM WISHLIST
(request => "deleteFromWishList"
 username => #username
 itemName => itemName
)

JSON RESPONSE
{"success": #success_status,
 "message" : #message
}

TASK = CHECK ITEM ON WISHLIST
(request => "checkItem"
 itemName => #itemname
 username => #username
)

JSON RESPONSE
{"success" : #success_status,
 "message" : #message
}












