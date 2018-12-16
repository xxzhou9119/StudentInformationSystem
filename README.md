Github Link: https://github.com/xxzhou9119/StudentInformationSystem

Elastic Beanstalk：http://studentinformationsystems-env.ye3ct3gm25.us-east-1.elasticbeanstalk.com/



For assignment 3

Course
1. GET

  ...webapi/courses/

  ...webapi/courses/{courseid} (...webapi/courses/001)

2. POST

  ...webapi/courses/

  body:

{

"boradId": "007",

"courseId": "007",

"department": "info",

"professorId": "a.x",

"studentIds": [],

"studentTaId": "liu.a"

}



Student

POST

...webapi/students/

body:

{
​        "courseIds": [],

        "department": "info",
    
        "emailId": YOUR TESTEMAIL,
    
        "firstName": "san",
    
        "lastName": "zhang",
    
        "studentId": "zhang.s"
    
    }

...webapi/students/zhang.s/register

body

 {
​      

      "SNSTopicArn": "arn:aws:sns:us-east-1:599961460399:course004",
    
       "boradId": "004",
    
        "courseId": "004",
    
        "department": "info",
    
        "id": "457aa757-7236-4224-8dcc-003aaaa99349",
    
        "professorId": "a.x",
    
        "studentIds": [
    
            "z.x"
    
        ],
    
        "studentTaId": "liu.a"
    
    }



Announcement

1. GET

   ...webapi/announcements/

...webapi/announcements/{boardId}-{announcementId}  (...webapi/announcements/004-02)

2. POST

...webapi/announcements/

 body:

 {

    "announcementId": "06",
    
    "announcementText": "aaaaaaaaaaaa",
    
    "boardId": "004" 

}



For assignment 4

POST

...webapi/courses/

body:

{

"boradId": "",

"courseId": "11111",

"department": "info",

"professorId": "a.x",

"studentIds": [],

"studentTaId": "liu.a"

}

Note: When you call the POST request, the returned Course is the original course(maybe with a snsTopicArn, but not the boardId), not the complete Course. You can get the complete Course with corresponding boardId after the step function ends. 



For BONUS:

In the original version, we can let the state machine directly access dynamodb, like the codes below:

"Create Registrar":{

​         "Type": "Task",

​            "Resource": "arn:aws:states:::dynamodb:putItem",

​            "Parameters": {

​                "TableName": "Registrar",

​                "Item": {

​                  "department": {

​                    "S.\$": "\$.department"

​                  },

​                  "offeringId": {

​                    "S.\$": "\$.courseId"

​                  },

​                  "offeringType": {

​                    "S": "Course"

​                  },

​                  "perUnitPrice": {

​                    "N": "1500"

​                  },

​                  "registrationId": {

​                    "S.\$": "\$.courseId"

​                  }

​                }

​            },

​            "ResultPath": "$.DynamoDB",

​          "Next": "Save Board"

​      },



Regarding the BONUS, I use a lambda function to expose a POST url and replace the previous task with code below :

 "Create Registrar":{

​        "Type": "Task",

​        "Resource": "arn:aws:lambda:us-east-1:599961460399:function:CreateRegistrar",

​        "Next": "Save Board"

  },



